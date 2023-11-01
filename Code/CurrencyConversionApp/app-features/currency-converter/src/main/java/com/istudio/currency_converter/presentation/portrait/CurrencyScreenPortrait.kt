package com.istudio.currency_converter.presentation.portrait

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.istudio.core_ui.composables.DropDownField
import com.istudio.core_ui.composables.GridInput
import com.istudio.core_ui.composables.InputTextField
import com.istudio.core_ui.theme.LocalSpacing
import com.istudio.models.local.CurrencyEntity
import com.istudio.models.local.RatesEntity

@Composable
fun CurrencyScreenPortrait(
    curriencyList : List<CurrencyEntity>,
    curriencyRatesList : List<RatesEntity>,
    currencyInputText : String,
    onKeyBoardOutsideClick: () -> Unit,
    currencyInputChange : (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(LocalSpacing.current.spaceExtraSmall)
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    onKeyBoardOutsideClick.invoke()
                })
            },
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.Center
    ) {

        InputTextField(
            modifier = Modifier.fillMaxWidth(),
            currencyInputText = currencyInputText,
            currencyInputChange = { currencyInputChange.invoke(it) }
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {

            Spacer(
                modifier = Modifier
                .height(LocalSpacing.current.spaceExtraSmall)
                .width(5.dp).weight(1f)
            )

            Box() {
                DropDownField(dataList = curriencyList)
            }
        }

        Spacer(modifier = Modifier.height(LocalSpacing.current.spaceExtraSmall))

        GridInput(){ selectedItem ->

        }
    }
}