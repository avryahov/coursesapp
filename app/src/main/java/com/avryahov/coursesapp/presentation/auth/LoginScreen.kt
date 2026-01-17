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
import androidx.compose.ui.text.style.TextAlign
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
fun LoginScreen(
    onRegisterClick: () -> Unit,
    onForgotPasswordClick: () -> Unit,
    onLoginSuccess: () -> Unit
) {
    val context = LocalContext.current
    val viewModel: AuthViewModel = hiltViewModel()

    val email = viewModel.email
    val password = viewModel.password
    val isEmailValid = viewModel.isEmailValid
    val canLogin = viewModel.canLogin
    val uiState = viewModel.uiState

    HandleAuthEffects(viewModel.effect, context)

    val emailError = (!isEmailValid && email.isNotEmpty())
        .takeIf { it }
        ?.let { stringResource(R.string.error_email_invalid) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = stringResource(R.string.login_title),
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
            modifier = Modifier.padding(bottom = 18.dp)
        )

        ActionButton(
            text = stringResource(R.string.login_button),
            onClick = { viewModel.login(onLoginSuccess) },
            enabled = canLogin,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        )

        Row(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 8.dp)
        ) {
            Text(
                text = stringResource(R.string.no_account_yet),
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                text = stringResource(R.string.register_action),
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .padding(start = 4.dp)
                    .clickable { onRegisterClick() }
            )
        }

        Text(
            text = stringResource(R.string.forgot_password),
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(top = 12.dp, bottom = 24.dp)
                .align(Alignment.CenterHorizontally)
                .clickable { onForgotPasswordClick() }
        )

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
                        // Ignore malformed URLs or missing browser
                    }
                }
            }
        }
    }
}