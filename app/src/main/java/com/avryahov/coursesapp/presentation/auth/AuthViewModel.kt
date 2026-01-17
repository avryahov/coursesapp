package com.avryahov.coursesapp.presentation.auth

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.avryahov.coursesapp.R
import com.avryahov.coursesapp.data.model.AuthUser
import com.avryahov.coursesapp.data.repository.AuthError
import com.avryahov.coursesapp.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

data class AuthUiState(
    val errorMessageResId: Int? = null
)

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    var uiState by mutableStateOf(AuthUiState())
        private set

    fun clearError() {
        uiState = uiState.copy(errorMessageResId = null)
    }

    fun login(email: String, password: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            val result = authRepository.login(email, password)
            if (result.isSuccess) {
                onSuccess()
            } else {
                val errorResId = when (result.exceptionOrNull()) {
                    is AuthError.InvalidCredentials -> R.string.error_invalid_credentials
                    else -> R.string.error_unknown
                }
                uiState = uiState.copy(errorMessageResId = errorResId)
            }
        }
    }

    fun register(user: AuthUser, onSuccess: () -> Unit) {
        viewModelScope.launch {
            val result = authRepository.register(user)
            if (result.isSuccess) {
                onSuccess()
            } else {
                uiState = uiState.copy(errorMessageResId = R.string.error_unknown)
            }
        }
    }
}