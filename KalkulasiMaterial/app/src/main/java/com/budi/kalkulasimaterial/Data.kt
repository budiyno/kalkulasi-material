package com.budi.kalkulasimaterial

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Data(
        val vol : String,
        val cap : String,
        val pf : String,
        val car : String,
        val label : String
):Parcelable
