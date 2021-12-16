package com.example.fsmaplibrary.utils

import com.example.fsmaplibrary.R

object MarkerIconResourceProvider {

    fun getIconForType(type:String): Int {
        return when(type){
            "restaurant" -> R.raw.restaurant
            "shop" -> R.raw.shop
            else -> R.raw.pin
        }
    }
}