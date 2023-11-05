package com.istudio.currency_result.presentation.portrait

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.istudio.core_ui.composables.AnimatedCard
import com.istudio.currency_result.presentation.states.CurrencyResultScreenUiState

@Composable
fun CurrencyResultPortrait(
    state: MutableState<CurrencyResultScreenUiState>
) {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val paddingModifier = Modifier.padding(10.dp)
        val cardPadding = 16.dp

        AnimatedCard(
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .padding(cardPadding)
                .fillMaxWidth(),
        ) {
            Box(
                modifier = Modifier
                    .height(200.dp)
                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center
                ) {

                    Text(
                        text = "User entered currency",
                        modifier = paddingModifier,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = state.value.userEnteredCardDetails,
                        modifier = paddingModifier,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )

                }
            }
        }

        Spacer(modifier = Modifier
            .height(10.dp)
            .fillMaxWidth())

        AnimatedCard(
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
        ) {
            Box(
                modifier = Modifier
                    .height(200.dp)
                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center
                ) {

                    Text(
                        text = "Converted currency",
                        modifier = paddingModifier,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = state.value.userCalculatedCardDetails,
                        modifier = paddingModifier,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}