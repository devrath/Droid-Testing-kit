package com.istudio.common.navigation

sealed class Screen(val route : String) {
    companion object{
        const val userFromEnteredCurrency_key = "userFromEnteredCurrency"
        const val userFromEnteredCurrencyType_key = "userFromEnteredCurrencyType"

        const val userFromEnteredCurrencyKey_key = "userFromEnteredCurrencyKey"
        const val userFromEnteredCurrencyName_key = "userFromEnteredCurrencyName"
        const val currencyToRateKey_key = "currencyToRateKey"
        const val currencyToRateValue_key = "currencyToRateValue"
    }
    object CurrencyConverter : Screen(
        route = "currency_conversion_screen"
    )
    object CurrencyResult : Screen(
        route = "currency_result_screen/" +
                "{$userFromEnteredCurrency_key}" + "/" +
                "{$userFromEnteredCurrencyType_key}" + "/" +
                "{$userFromEnteredCurrencyKey_key}" + "/" +
                "{$userFromEnteredCurrencyName_key}" + "/" +
                "{$currencyToRateKey_key}" + "/" +
                "{$currencyToRateValue_key}"
    ){
        fun passParameters(
            userFromEnteredCurrency : String,
            userFromEnteredCurrencyType : String,
            userFromEnteredCurrencyKey : String,
            userFromEnteredCurrencyName : String,
            currencyToRateKey : String,
            currencyToRateValue : String,
        ): String {
            return "currency_result_screen/" +
                    "$userFromEnteredCurrency/" +
                    "$userFromEnteredCurrencyType/" +
                    "$userFromEnteredCurrencyKey/" +
                    "$userFromEnteredCurrencyName/" +
                    "$currencyToRateKey/" +
                    "$currencyToRateValue"
        }
    }
}