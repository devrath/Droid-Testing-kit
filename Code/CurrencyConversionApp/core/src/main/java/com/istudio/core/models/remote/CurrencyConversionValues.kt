package com.istudio.core.models.remote

import com.google.gson.annotations.SerializedName

data class CurrencyConversionValues (

	@SerializedName("disclaimer") val disclaimer : String,
	@SerializedName("license") val license : String,
	@SerializedName("timestamp") val timestamp : Int,
	@SerializedName("base") val base : String,
	@SerializedName("rates") val rates : Rates
)