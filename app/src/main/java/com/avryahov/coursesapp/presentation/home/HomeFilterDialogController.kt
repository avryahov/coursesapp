package com.avryahov.coursesapp.presentation.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import com.avryahov.coursesapp.R
import com.avryahov.coursesapp.component.dialog.CustomFilterDialog
import com.avryahov.coursesapp.component.dialog.FilterOption
import com.avryahov.coursesapp.data.model.PriceFilter

@Composable
fun rememberPriceFilterDialogController(
    currentFilter: PriceFilter?,
    onApply: (PriceFilter?) -> Unit
): Pair<Boolean, (Boolean) -> Unit> {
    var isDialogVisible by remember { mutableStateOf(false) }
    var selectedInDialog by remember { mutableStateOf(currentFilter) }

    if (isDialogVisible) {
        CustomFilterDialog(
            titleResId = R.string.filter_by_price,
            options = listOf(
                FilterOption(null, stringResource(R.string.any_price)),
                FilterOption(PriceFilter.UNDER_1000, stringResource(R.string.price_under_1000)),
                FilterOption(PriceFilter.FROM_1000_TO_5000, stringResource(R.string.price_1000_5000)),
                FilterOption(PriceFilter.OVER_5000, stringResource(R.string.price_over_5000))
            ),
            selectedOption = selectedInDialog,
            onOptionSelected = { selectedInDialog = it },
            onApply = {
                onApply(selectedInDialog)
                isDialogVisible = false
            },
            onDismiss = { isDialogVisible = false }
        )
    }

    return isDialogVisible to { isDialogVisible = it }
}