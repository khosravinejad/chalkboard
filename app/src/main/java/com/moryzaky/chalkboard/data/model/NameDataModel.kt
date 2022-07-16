package com.moryzaky.chalkboard.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Morteza Khosravinejad on 16/07/2022.
 */

data class NameDataModel(
    @SerializedName("title") val title: String,
    @SerializedName("first") val firstName: String,
    @SerializedName("last") val lastName: String?
)