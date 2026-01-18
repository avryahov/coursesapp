package com.avryahov.coursesapp.data.repository

import com.avryahov.coursesapp.data.model.Course

interface CourseRepository {
    suspend fun getCourses(): List<Course>
}