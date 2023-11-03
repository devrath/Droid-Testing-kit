package com.istudio.currency_converter.presentation.landscape

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.istudio.core_ui.composables.DropDownField
import com.istudio.core_ui.composables.GridInput
import com.istudio.core_ui.composables.InputTextField
import com.istudio.core_ui.theme.LocalSpacing
import com.istudio.models.local.CurrencyEntity
import com.istudio.models.local.RatesEntity

@Composable
fun CurrencyScreenLandscape(
    curriencyList : List<CurrencyEntity>,
    curriencyRatesList : List<RatesEntity>,
    currencyInputText : String,
    isCurrencyFieldError : Boolean = false,
    isCurrencyValueDropDownError : Boolean = false,
    onKeyBoardOutsideClick: () -> Unit,
    currencyInputChange : (String) -> Unit,
    setRatesItemSelection : (Int) -> Unit,
    onCurrencyDropDownSelection : (CurrencyEntity) -> Unit,
    // -----> Drop down data
    currencyTypeState: MutableState<String> = mutableStateOf(""),
    updateDropDownState : (MutableState<String>) -> Unit,
) {

    Row(modifier = Modifier
        .fillMaxSize()
        .padding(LocalSpacing.current.spaceExtraSmall)
        .pointerInput(Unit) {
            detectTapGestures(onTap = {
                onKeyBoardOutsideClick.invoke()
            })
        }
    ) {
        Column(
            modifier = Modifier.weight(1f).fillMaxHeight()
        ) {
            Column(
                modifier = Modifier.fillMaxWidth().weight(1f),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                InputTextField(
                    modifier = Modifier.fillMaxWidth(),
                    currencyInputText = currencyInputText,
                    isError = isCurrencyFieldError,
                    currencyInputChange = { currencyInputChange.invoke(it) }
                )

                Spacer(modifier = Modifier.height(LocalSpacing.current.spaceExtraSmall))

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
                        DropDownField(
                            dataList = curriencyList,
                            isError = isCurrencyValueDropDownError,
                            selectionAction = { itemSelection ->
                                onCurrencyDropDownSelection.invoke(itemSelection)
                            },
                            currencyTypeState = currencyTypeState,
                            updateDropDownState = updateDropDownState,
                        )
                    }
                }

                Spacer(modifier = Modifier.height(LocalSpacing.current.spaceExtraSmall))

                Text(
                    text = "Output",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier
                        .weight(1.8f)
                        .padding(
                            horizontal = LocalSpacing.current.spaceExtraSmall,
                            vertical = 20.dp
                        ),
                    textAlign = TextAlign.Start,
                    maxLines = 1
                )
            }
        }
        Column(
            modifier = Modifier.weight(1f).fillMaxHeight()
        ) {
            GridInput(
                data = curriencyRatesList,
                modifier = Modifier.fillMaxWidth().weight(1f),
            ){ selectedItem ->
                setRatesItemSelection.invoke(selectedItem)
            }
        }
    }
}

@Preview(
    showSystemUi = true,
    device = "spec:width=411dp,height=891dp,dpi=420,isRound=false,chinSize=0dp,orientation=landscape"
)
@Composable
private fun CurrentScreen() {
   /* CurrencyScreenLandscape(
        curriencyList = emptyList(),
        curriencyRatesList = emptyList(),
        currencyInputText = "100",
        onKeyBoardOutsideClick = { },
        currencyInputChange = { },
        setRatesItemSelection = { },
        onCurrencyDropDownSelection = { },
        currencyTypeState = { }
    )*/
}