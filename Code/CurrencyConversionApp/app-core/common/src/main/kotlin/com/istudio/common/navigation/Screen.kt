package com.istudio.common.navigation

import com.istudio.common.navigation.Route.CURRENCY_RESULT_SCREEN

sealed class Screen(val route : String) {
    companion object{
        const val userEnteredCurrencyValue_key = "userEnteredCurrencyValue"
    }
    object CurrencyConverter : Screen(
        route = "currency_conversion_screen"
    )
    object CurrencyResult : Screen(
        route = "currency_result_screen/{$userEnteredCurrencyValue_key}"
    ){
        fun passCurrencyValue(userEnteredCurrencyValue:String) : String{
            return this.route.replace(
                oldValue = "{$userEnteredCurrencyValue_key}",
                newValue = userEnteredCurrencyValue
            )
        }
    }
}