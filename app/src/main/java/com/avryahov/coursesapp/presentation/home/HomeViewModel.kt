package com.avryahov.coursesapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.avryahov.coursesapp.data.model.Course
import com.avryahov.coursesapp.data.model.PriceFilter
import com.avryahov.coursesapp.data.repository.CourseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class HomeUiState(
    val allCourses: List<Course> = emptyList(),
    val filteredCourses: List<Course> = emptyList(),
    val searchQuery: String = "",
    val selectedPriceFilter: PriceFilter? = null,
    val sortDirection: SortDirection = SortDirection.Descending
)

enum class SortDirection {
    Ascending, Descending
}

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val courseRepository: CourseRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        loadCourses()
    }

    private fun loadCourses() {
        viewModelScope.launch {
            val allCourses = courseRepository.getCourses()
            _uiState.value = HomeUiState(allCourses = allCourses)
            applyFilters()
        }
    }

    fun onSearchQueryChanged(query: String) {
        updateState { copy(searchQuery = query) }
        applyFilters()
    }

    fun onPriceFilterSelected(filter: PriceFilter?) {
        updateState { copy(selectedPriceFilter = filter) }
        applyFilters()
    }

    fun onSortToggle() {
        val newDirection = when (uiState.value.sortDirection) {
            SortDirection.Ascending -> SortDirection.Descending
            SortDirection.Descending -> SortDirection.Ascending
        }
        updateState { copy(sortDirection = newDirection) }
        applyFilters()
    }

    fun onToggleLike(courseId: Int) {
        val currentState = _uiState.value
        val updatedAllCourses = currentState.allCourses.map { course ->
            if (course.id == courseId) {
                course.copy(hasLike = !course.hasLike)
            } else {
                course
            }
        }
        _uiState.value = currentState.copy(allCourses = updatedAllCourses)
        applyFilters()
    }

    private fun applyFilters() {
        val state = _uiState.value
        var result = state.allCourses

        if (state.searchQuery.isNotBlank()) {
            val q = state.searchQuery.lowercase().trim()
            result = result.filter { course ->
                course.title.lowercase().contains(q) ||
                        course.text.lowercase().contains(q)
            }
        }

        result = when (state.selectedPriceFilter) {
            PriceFilter.UNDER_1000 -> result.filter { it.price < 1000 }
            PriceFilter.FROM_1000_TO_5000 -> result.filter { it.price in 1000..5000 }
            PriceFilter.OVER_5000 -> result.filter { it.price > 5000 }
            null -> result
        }

        result = when (state.sortDirection) {
            SortDirection.Ascending -> result.sortedBy { it.publishDate }
            SortDirection.Descending -> result.sortedByDescending { it.publishDate }
        }

        _uiState.value = state.copy(filteredCourses = result)
    }

    private fun updateState(block: HomeUiState.() -> HomeUiState) {
        _uiState.value = block(_uiState.value)
    }
}