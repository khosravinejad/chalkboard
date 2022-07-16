package com.moryzaky.chalkboard.data.source.remote.response

import com.google.gson.annotations.SerializedName
import com.moryzaky.chalkboard.data.model.PersonDataModel

/**
 * Created by Morteza Khosravinejad on 16/07/2022.
 */

data class PersonResponse(
    @SerializedName("results") val results: List<PersonDataModel>,
    @SerializedName("info") val info: InfoResponse
)