package com.istudio.core.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class CurrencyConversionValues(
    @SerialName("disclaimer") var disclaimer: String? = null,
    @SerialName("license") var license: String? = null,
    @SerialName("timestamp") var timestamp: Int? = null,
    @SerialName("base") var base: String? = null,
    @SerialName("rates") var rates: Rates? = Rates()
)