package com.avryahov.coursesapp.presentation.favourite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.avryahov.coursesapp.data.model.Course
import com.avryahov.coursesapp.data.repository.CourseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class FavouriteUiState(
    val favouriteCourses: List<Course> = emptyList()
)

@HiltViewModel
class FavouriteViewModel @Inject constructor(
    private val courseRepository: CourseRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(FavouriteUiState())
    val uiState: StateFlow<FavouriteUiState> = _uiState

    init {
        loadCourses()
    }

    private fun loadCourses() {
        viewModelScope.launch {
            val allCourses = courseRepository.getCourses()
            val favorites = allCourses.filter { it.hasLike }
            _uiState.value = FavouriteUiState(favouriteCourses = favorites)
        }
    }

    fun onToggleLike(courseId: Int) {
        val updatedCourses = _uiState.value.favouriteCourses.mapNotNull { course ->
            if (course.id == courseId) {
                val newHasLike = !course.hasLike
                if (newHasLike) {
                    course.copy(hasLike = true)
                } else {
                    null
                }
            } else {
                course
            }
        }
        _uiState.value = FavouriteUiState(favouriteCourses = updatedCourses)
    }
}