package com.avryahov.coursesapp.component.input

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import com.avryahov.coursesapp.R

@Composable
fun EmailTextField(
    value: String,
    onValueChange: (String) -> Unit,
    isError: Boolean = false,
    errorMessage: String? = null,
    modifier: Modifier = Modifier
) {
    BaseTextField(
        label = stringResource(R.string.email_label),
        value = value,
        onValueChange = onValueChange,
        isError = isError,
        errorMessage = errorMessage,
        placeholder = stringResource(R.string.email_placeholder),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        modifier = modifier
    )
}