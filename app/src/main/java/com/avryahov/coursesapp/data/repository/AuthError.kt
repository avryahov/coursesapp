package com.avryahov.coursesapp.data.repository

sealed class AuthError : Exception() {
    class InvalidCredentials : AuthError()
}