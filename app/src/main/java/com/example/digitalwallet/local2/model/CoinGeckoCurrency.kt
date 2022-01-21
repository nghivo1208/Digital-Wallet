package com.example.digitalwallet.local2.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Parcelize
@Entity
data class CoingeckoCurrency(
    @PrimaryKey val base: String,
    val name: String,
    val buyPrice: Double,
    val sellPrice: Double,
    val icon: String
) : Parcelable
