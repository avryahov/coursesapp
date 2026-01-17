package com.avryahov.coursesapp.presentation.auth

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.avryahov.coursesapp.R
import com.avryahov.coursesapp.component.button.ActionButton
import com.avryahov.coursesapp.component.button.SocialButton
import com.avryahov.coursesapp.component.dialog.CustomErrorDialog
import com.avryahov.coursesapp.component.input.EmailTextField
import com.avryahov.coursesapp.component.input.PasswordTextField
import com.avryahov.coursesapp.data.model.AuthEffect
import com.avryahov.coursesapp.ui.theme.AppColors
import kotlinx.coroutines.flow.Flow

@Composable
fun RegistrationScreen(
    onLoginClick: () -> Unit,
    onRegistrationSuccess: () -> Unit
) {
    val context = LocalContext.current
    val viewModel: AuthViewModel = hiltViewModel()

    val email = viewModel.email
    val password = viewModel.password
    val confirmPassword = viewModel.confirmPassword
    val isEmailValid = viewModel.isEmailValid
    val isPasswordMatch = viewModel.isPasswordMatch
    val canRegister = viewModel.canRegister
    val uiState = viewModel.uiState

    HandleAuthEffects(viewModel.effect, context)

    val emailError = (!isEmailValid && email.isNotEmpty())
        .takeIf { it }
        ?.let { stringResource(R.string.error_email_invalid) }

    val passwordError = (!isPasswordMatch && confirmPassword.isNotEmpty())
        .takeIf { it }
        ?.let { stringResource(R.string.error_password_mismatch) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = stringResource(R.string.registration_title),
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(top = 160.dp, bottom = 40.dp)
        )

        EmailTextField(
            value = email,
            onValueChange = viewModel::onEmailChanged,
            isError = emailError != null,
            errorMessage = emailError,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        PasswordTextField(
            label = stringResource(R.string.password_label),
            value = password,
            onValueChange = viewModel::onPasswordChanged,
            placeholder = stringResource(R.string.password_placeholder),
            modifier = Modifier.padding(bottom = 12.dp)
        )

        PasswordTextField(
            label = stringResource(R.string.confirm_password_label),
            value = confirmPassword,
            onValueChange = viewModel::onConfirmPasswordChanged,
            placeholder = stringResource(R.string.confirm_password_placeholder),
            isError = passwordError != null,
            errorMessage = passwordError,
            modifier = Modifier.padding(bottom = 18.dp)
        )

        ActionButton(
            text = stringResource(R.string.register_button),
            onClick = { viewModel.register(onRegistrationSuccess) }, // → идёт на LoginScreen
            enabled = canRegister,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        )

        Row(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 24.dp)
        ) {
            Text(
                text = stringResource(R.string.already_have_account),
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                text = stringResource(R.string.login_action),
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .padding(start = 4.dp)
                    .clickable { onLoginClick() }
            )
        }

        HorizontalDivider(
            color = MaterialTheme.colorScheme.outline,
            thickness = 1.dp,
            modifier = Modifier.padding(top = 20.dp, bottom = 40.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            SocialButton(
                iconResId = R.drawable.ic_vk,
                contentDescription = stringResource(R.string.social_vk_content_desc),
                onClick = { viewModel.onVkClick() },
                containerColor = AppColors.VK,
                modifier = Modifier.weight(1f)
            )

            SocialButton(
                iconResId = R.drawable.ic_ok,
                contentDescription = stringResource(R.string.social_ok_content_desc),
                onClick = { viewModel.onOkClick() },
                containerColor = AppColors.OK,
                modifier = Modifier.weight(1f)
            )
        }
    }

    if (uiState.errorMessageResId != null) {
        CustomErrorDialog(
            message = stringResource(uiState.errorMessageResId),
            onDismiss = { viewModel.clearError() }
        )
    }
}


@Composable
private fun HandleAuthEffects(
    effect: Flow<AuthEffect>,
    context: Context
) {
    LaunchedEffect(Unit) {
        effect.collect { authEffect ->
            when (authEffect) {
                is AuthEffect.OpenSocialUrl -> {
                    try {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(authEffect.url))
                        context.startActivity(intent)
                    } catch (e: Exception) {
                        // not throws
                    }
                }
            }
        }
    }
}