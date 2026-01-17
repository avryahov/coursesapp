package com.avryahov.coursesapp.data.model

sealed class AuthEffect {
    data class OpenSocialUrl(val url: String) : AuthEffect()
}