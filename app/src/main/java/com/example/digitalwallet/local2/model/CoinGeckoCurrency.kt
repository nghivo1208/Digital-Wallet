package com.example.digitalwallet.local2.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Parcelize
@Entity
data class CoinGeckoCurrency(
    @PrimaryKey val marketCap: Int,
    val name: String,
    val currentPrice: Double,
    val symbol: String,
    val image: String
) : Parcelable
