package com.avryahov.coursesapp.component.dialog

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.avryahov.coursesapp.R
import com.avryahov.coursesapp.ui.theme.AppColors

data class FilterOption<T>(
    val id: T,
    val label: String
)

@Composable
private fun DialogTitle(titleResId: Int) {
    Text(
        text = stringResource(titleResId),
        style = MaterialTheme.typography.titleLarge,
        color = AppColors.OnBackground
    )
}

@Composable
private fun <T> FilterOptionsList(
    options: List<FilterOption<T>>,
    selectedOption: T?,
    onOptionSelected: (T) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(vertical = 8.dp)) {
        options.forEach { option ->
            FilterOptionItem(
                option = option,
                isSelected = option.id == selectedOption,
                onClick = { onOptionSelected(option.id) }
            )
        }
    }
}

@Composable
private fun <T> FilterOptionItem(
    option: FilterOption<T>,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = isSelected,
            onClick = onClick,
            colors = RadioButtonDefaults.colors(
                selectedColor = AppColors.PrimaryGreen,
                unselectedColor = AppColors.Outline
            )
        )
        Text(
            text = option.label,
            style = MaterialTheme.typography.bodyMedium,
            color = AppColors.OnBackground,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

@Composable
private fun ApplyButton(onClick: () -> Unit) {
    TextButton(
        onClick = onClick,
        colors = androidx.compose.material3.ButtonDefaults.textButtonColors(
            contentColor = AppColors.PrimaryGreen
        )
    ) {
        Text(
            text = stringResource(R.string.apply),
            style = MaterialTheme.typography.labelLarge
        )
    }
}

@Composable
private fun CancelButton(onClick: () -> Unit) {
    TextButton(
        onClick = onClick,
        colors = androidx.compose.material3.ButtonDefaults.textButtonColors(
            contentColor = AppColors.PrimaryGreen
        )
    ) {
        Text(
            text = stringResource(R.string.cancel),
            style = MaterialTheme.typography.labelLarge
        )
    }
}

@Composable
fun <T> CustomFilterDialog(
    titleResId: Int,
    options: List<FilterOption<T>>,
    selectedOption: T?,
    onOptionSelected: (T) -> Unit,
    onApply: () -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { DialogTitle(titleResId) },
        text = {
            FilterOptionsList(
                options = options,
                selectedOption = selectedOption,
                onOptionSelected = onOptionSelected,
                modifier = modifier
            )
        },
        confirmButton = { ApplyButton(onClick = { onApply(); onDismiss() }) },
        dismissButton = { CancelButton(onClick = onDismiss) }
    )
}
