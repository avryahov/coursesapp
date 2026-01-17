package com.avryahov.coursesapp.data.repository

import com.avryahov.coursesapp.data.model.AuthUser

// пока всё в памяти
class LocalAuthRepository : AuthRepository {
    private var currentUser: AuthUser? = null

    override suspend fun register(user: AuthUser): Result<Unit> {
        return runCatching {
            currentUser = user
        }
    }

    override suspend fun login(email: String, password: String): Result<AuthUser> {
        if (currentUser?.email == email && currentUser?.password == password) {
            return Result.success(currentUser!!)
        } else {
            return Result.failure(AuthError.InvalidCredentials())
        }
    }

    override suspend fun getCurrentUser(): AuthUser? {
        return currentUser
    }
}