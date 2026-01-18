package com.avryahov.coursesapp.di

import com.avryahov.coursesapp.data.repository.AuthRepository
import com.avryahov.coursesapp.data.repository.CourseRepository
import com.avryahov.coursesapp.data.repository.DefaultCourseRepository
import com.avryahov.coursesapp.data.repository.LocalAuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideAuthRepository(): AuthRepository {
        return LocalAuthRepository()
    }

    @Provides
    @Singleton
    fun provideCourseRepository(): CourseRepository {
        return DefaultCourseRepository()
    }
}