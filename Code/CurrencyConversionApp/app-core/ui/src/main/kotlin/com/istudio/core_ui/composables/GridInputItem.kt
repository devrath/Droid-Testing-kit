package com.istudio.core_ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.istudio.core_ui.theme.LocalSpacing
import com.istudio.models.local.CurrencyAndRates
import com.istudio.models.local.RatesEntity

@Composable
fun GridInputItem(
    item: CurrencyAndRates,
    position: Int,
    onClick: (Int) -> Unit
) {
    Box(
        modifier = Modifier
            .padding(LocalSpacing.current.spaceExtraSmall)
            .aspectRatio(1f)
            .clip(RoundedCornerShape(5.dp))
            .background(
                color =
                if (item.rates.isItemSelected.value) {
                    // Indicate selected
                    MaterialTheme.colorScheme.tertiaryContainer
                } else {
                    // Indicate not selected
                    MaterialTheme.colorScheme.primaryContainer
                }
            )
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(
                    color = MaterialTheme.colorScheme.onSurface
                ),
                onClick = { onClick.invoke(position) }
            )
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = item.currency.currencyName.toString(),
            color = MaterialTheme.colorScheme.primary
        )
    }
}