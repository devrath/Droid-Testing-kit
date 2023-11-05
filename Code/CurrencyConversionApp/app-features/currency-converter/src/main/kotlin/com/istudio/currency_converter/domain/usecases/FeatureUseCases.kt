package com.istudio.currency_converter.domain.usecases

import com.istudio.currency_converter.domain.usecases.useCaseTypes.ApiErrorCodeValidationUseCase
import com.istudio.currency_converter.domain.usecases.useCaseTypes.CanUiBeDisplayedUseCase
import com.istudio.currency_converter.domain.usecases.useCaseTypes.GetCurrencyListDataFromDbUseCase
import com.istudio.currency_converter.domain.usecases.useCaseTypes.GetCurrencyRatesListDataFromDbUseCase
import com.istudio.currency_converter.domain.usecases.useCaseTypes.GetDataFromNetworkUseCase
import com.istudio.currency_converter.domain.usecases.useCaseTypes.InsertDataIntoDbUseCase
import com.istudio.currency_converter.domain.usecases.useCaseTypes.IsNewDataToBeFetchedFromServerUseCase
import com.istudio.currency_converter.domain.usecases.useCaseTypes.SaveTimeStampUseCase
import com.istudio.currency_converter.domain.usecases.useCaseTypes.SetRateGridSelectionUseCase
import com.istudio.currency_converter.domain.usecases.useCaseTypes.ValidateAllInputsForCalculationUseCase
import com.istudio.currency_converter.domain.usecases.useCaseTypes.ValidateCurrencyInputTypeUseCase
import com.istudio.currency_converter.domain.usecases.useCaseTypes.ValidateCurrencyInputValueUseCase

data class FeatureUseCases(
    // Use-Case:-> Network
    val network : GetDataFromNetworkUseCase,
    // Use-Case:-> Database
    val dbInsertAllData: InsertDataIntoDbUseCase,
    // Use-Case:-> Get Currencies data from DB
    val dbRetrieveCurrencies: GetCurrencyListDataFromDbUseCase,
    // Use-Case:-> Get Currency rates data from DB
    val dbRetrieveCurrencyRates: GetCurrencyRatesListDataFromDbUseCase,
    // Use-Case:-> Can UI be displayed
    val canUiBeDisplayedUseCase : CanUiBeDisplayedUseCase,
    // Use-Case:-> Save preferences to Datastore
    val saveTimeStampUseCase : SaveTimeStampUseCase,
    // Use-Case:-> Determine if new data has to be fetched from server
    val isNewDataToBeFetchedFromServerUseCase : IsNewDataToBeFetchedFromServerUseCase,
    // Use-Case:-> Selection of grid item on item selected
    val setRateGridSelectionUseCase : SetRateGridSelectionUseCase,
    // Use-Case:-> Validate the currency input value done from user
    val validateCurrencyInputValueUseCase : ValidateCurrencyInputValueUseCase,
    // Use-Case:-> Validate the currency input type done from user
    val validateCurrencyInputTypeUseCase : ValidateCurrencyInputTypeUseCase,
    // Use-Case:-> Validate the inputs involved in currency conversion
    val validateAllInputsForCalculationUseCase : ValidateAllInputsForCalculationUseCase,
    // Use-Case:-> Validate error codes for response
    val apiErrorCodeValidation : ApiErrorCodeValidationUseCase,
)
