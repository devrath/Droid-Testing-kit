package com.istudio.core.models.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class CurrencyConversionValues(
    @SerialName("disclaimer") val disclaimer: String? = null,
    @SerialName("license") val license: String? = null,
    @SerialName("timestamp") val timestamp: Int? = null,
    @SerialName("base") val base: String? = null,
    @SerialName("rates") val rates: Rates? = Rates()
)