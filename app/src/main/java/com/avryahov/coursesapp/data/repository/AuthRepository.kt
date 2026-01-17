package com.avryahov.coursesapp.data.repository

import com.avryahov.coursesapp.data.model.AuthUser

interface AuthRepository {
    suspend fun register(user: AuthUser): Result<Unit>
    suspend fun login(email: String, password: String): Result<AuthUser>
    suspend fun getCurrentUser(): AuthUser?
}