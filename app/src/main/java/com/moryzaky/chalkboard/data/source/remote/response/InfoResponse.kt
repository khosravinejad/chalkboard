package com.moryzaky.chalkboard.data.source.remote.response

import com.google.gson.annotations.SerializedName

/**
 * Created by Morteza Khosravinejad on 16/07/2022.
 */

data class InfoResponse(
    @SerializedName("results") val pageSize: Int,
    @SerializedName("page") val pageNumber: Int
)