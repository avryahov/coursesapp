package com.avryahov.coursesapp.component.dialog

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.avryahov.coursesapp.R
import com.avryahov.coursesapp.ui.theme.AppColors

@Composable
fun CustomErrorDialog(
    message: String,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = stringResource(R.string.error_title),
                style = MaterialTheme.typography.titleLarge,
                color = AppColors.OnBackground
            )
        },
        text = {
            Text(
                text = message,
                style = MaterialTheme.typography.bodyMedium,
                color = AppColors.OnBackground
            )
        },
        confirmButton = {
            TextButton(
                onClick = onDismiss,
                colors = androidx.compose.material3.ButtonDefaults.textButtonColors(
                    contentColor = AppColors.PrimaryGreen
                )
            ) {
                Text(
                    text = stringResource(R.string.ok_button),
                    style = MaterialTheme.typography.labelLarge
                )
            }
        }
    )
}