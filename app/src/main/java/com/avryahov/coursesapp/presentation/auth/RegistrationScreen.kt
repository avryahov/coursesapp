package com.avryahov.coursesapp.presentation.auth

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.avryahov.coursesapp.R
import com.avryahov.coursesapp.component.button.ActionButton
import com.avryahov.coursesapp.component.button.SocialButton
import com.avryahov.coursesapp.component.input.EmailTextField
import com.avryahov.coursesapp.component.input.PasswordTextField
import com.avryahov.coursesapp.ui.theme.AppColors

@Composable
fun RegistrationScreen(
    onLoginClick: () -> Unit,
    onRegistrationSuccess: () -> Unit,
    onVkClick: () -> Unit = {},
    onOkClick: () -> Unit = {}
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

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
            onValueChange = { email = it },
            modifier = Modifier.padding(bottom = 12.dp)
        )

        PasswordTextField(
            label = stringResource(R.string.password_label),
            value = password,
            onValueChange = { password = it },
            placeholder = stringResource(R.string.password_placeholder),
            modifier = Modifier.padding(bottom = 12.dp)
        )

        PasswordTextField(
            label = stringResource(R.string.confirm_password_label),
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            placeholder = stringResource(R.string.confirm_password_placeholder),
            modifier = Modifier.padding(bottom = 18.dp)
        )

        ActionButton(
            text = stringResource(R.string.register_button),
            onClick = onRegistrationSuccess,
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
                onClick = onVkClick,
                containerColor = AppColors.VK,
                modifier = Modifier.weight(1f)
            )

            SocialButton(
                iconResId = R.drawable.ic_ok,
                contentDescription = stringResource(R.string.social_ok_content_desc),
                onClick = onVkClick,
                containerColor = AppColors.OK,
                modifier = Modifier.weight(1f)
            )
        }
    }
}