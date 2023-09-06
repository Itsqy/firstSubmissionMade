package com.syatria.core.data.source.remote.network

import com.syatria.core.data.source.remote.response.ListUserResponse
import retrofit2.http.GET

interface ApiService {

    @GET("character")
    suspend fun getUsers(
    ): ListUserResponse


}