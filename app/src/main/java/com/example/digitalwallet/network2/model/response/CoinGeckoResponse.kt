package com.example.digitalwallet.network2.model.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CoinGeckoCurrencyResponse(
    @Json(name = "name") val name: String?,
    @Json(name = "symbol") val symbol: String?,
    @Json(name = "current_price") val currentPrice: Double?,
    @Json(name = "image") val image: String?,
    @Json(name = "market_cap_rank") val marketCapRank: String?
)
