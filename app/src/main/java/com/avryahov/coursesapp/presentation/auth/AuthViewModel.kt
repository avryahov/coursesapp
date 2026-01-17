package com.avryahov.coursesapp.presentation.auth

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.avryahov.coursesapp.R
import com.avryahov.coursesapp.data.model.AuthEffect
import com.avryahov.coursesapp.data.model.AuthUser
import com.avryahov.coursesapp.data.repository.AuthError
import com.avryahov.coursesapp.data.repository.AuthRepository
import com.avryahov.coursesapp.util.ValidationConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class RegistrationState(
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val isEmailValid: Boolean = false,
    val isPasswordMatch: Boolean = true,
    val canRegister: Boolean = false
)

data class AuthUiState(
    val errorMessageResId: Int? = null
)

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    var registrationState by mutableStateOf(RegistrationState())
        private set

    var uiState by mutableStateOf(AuthUiState())
        private set

    val vkAuthUrl = "https://vk.com"
    val okAuthUrl = "https://ok.ru"

    private val _effect = MutableSharedFlow<AuthEffect>()
    val effect = _effect.asSharedFlow()

    // --- Getters for UI ---
    val email: String get() = registrationState.email
    val password: String get() = registrationState.password
    val confirmPassword: String get() = registrationState.confirmPassword
    val isEmailValid: Boolean get() = registrationState.isEmailValid
    val isPasswordMatch: Boolean get() = registrationState.isPasswordMatch
    val canRegister: Boolean get() = registrationState.canRegister
    val canLogin: Boolean get() = isEmailValid && password.isNotEmpty()

    fun clearError() {
        uiState = uiState.copy(errorMessageResId = null)
    }

    fun onEmailChanged(email: String) {
        val emailRegex = Regex(ValidationConstants.EMAIL_REGEX)
        val isValid = emailRegex.matches(email)
        registrationState = registrationState.copy(
            email = email,
            isEmailValid = isValid
        )
        updateCanRegister()
    }

    fun onPasswordChanged(password: String) {
        registrationState = registrationState.copy(password = password)
        validatePasswordsMatch()
        updateCanRegister()
    }

    fun onConfirmPasswordChanged(confirmPassword: String) {
        registrationState = registrationState.copy(confirmPassword = confirmPassword)
        validatePasswordsMatch()
        updateCanRegister()
    }

    private fun validatePasswordsMatch() {
        val match = registrationState.password == registrationState.confirmPassword
        registrationState = registrationState.copy(isPasswordMatch = match)
    }

    private fun updateCanRegister() {
        val state = registrationState
        registrationState = state.copy(
            canRegister = state.isEmailValid &&
                    state.password.isNotEmpty() &&
                    state.confirmPassword.isNotEmpty() &&
                    state.isPasswordMatch
        )
    }

    fun register(onSuccess: () -> Unit) {
        if (registrationState.canRegister) {
            viewModelScope.launch {
                val result = authRepository.register(
                    AuthUser(
                        email = registrationState.email,
                        password = registrationState.password
                    )
                )
                if (result.isSuccess) {
                    onSuccess()
                } else {
                    uiState = uiState.copy(errorMessageResId = R.string.error_unknown)
                }
            }
        }
    }

    fun login(onSuccess: () -> Unit) {
        if (!canLogin) return
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

    fun onVkClick() {
        viewModelScope.launch {
            _effect.emit(AuthEffect.OpenSocialUrl(vkAuthUrl))
        }
    }

    fun onOkClick() {
        viewModelScope.launch {
            _effect.emit(AuthEffect.OpenSocialUrl(okAuthUrl))
        }
    }
}