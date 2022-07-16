package com.moryzaky.chalkboard.data.model

import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * Created by Morteza Khosravinejad on 16/07/2022.
 */

data class BirthdayDataModel(
    @SerializedName("date") val date: Date,
    @SerializedName("age") val age: Int
)