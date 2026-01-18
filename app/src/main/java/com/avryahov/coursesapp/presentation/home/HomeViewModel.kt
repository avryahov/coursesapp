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
    val courses: List<Course> = emptyList(),
    val searchQuery: String = "",
    val selectedPriceFilter: PriceFilter? = null
)

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
            updateState { copy(courses = allCourses) }
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

    private fun applyFilters() {
        val state = _uiState.value
        val filtered = filterCourses(
            courses = state.courses,
            query = state.searchQuery,
            priceFilter = state.selectedPriceFilter
        )
        _uiState.value = state.copy(courses = filtered)
    }

    private fun updateState(block: HomeUiState.() -> HomeUiState) {
        _uiState.value = block(_uiState.value)
    }
}

fun filterCourses(
    courses: List<Course>,
    query: String,
    priceFilter: PriceFilter?
): List<Course> {
    var result = courses

    if (query.isNotBlank()) {
        val q = query.lowercase().trim()
        result = result.filter { course ->
            course.title.lowercase().contains(q) ||
                    course.text.lowercase().contains(q)
        }
    }

    result = when (priceFilter) {
        PriceFilter.UNDER_1000 -> result.filter { it.priceAsInt < 1000 }
        PriceFilter.FROM_1000_TO_5000 -> result.filter { it.priceAsInt in 1000..5000 }
        PriceFilter.OVER_5000 -> result.filter { it.priceAsInt > 5000 }
        null -> result
    }

    return result
}