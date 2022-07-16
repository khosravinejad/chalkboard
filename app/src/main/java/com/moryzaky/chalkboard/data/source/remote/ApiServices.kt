package com.moryzaky.chalkboard.data.source.remote

import com.moryzaky.chalkboard.data.source.remote.response.PersonResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Morteza Khosravinejad on 16/07/2022.
 */

interface ApiServices {
    companion object {
        const val BASE_URL = "https://randomuser.me/"
    }

    // It's not a correct api url, I split it from the main url to show how use base url + api urls
    @GET("api/?seed=chalkboard&inc=name,dob")
    suspend fun getBirthdayList(
        @Query("results") pageSize: Int,
        @Query("page") pageNumber: Int
    ): Response<PersonResponse>
}