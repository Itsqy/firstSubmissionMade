package com.syatria.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListUserResponse(


    @field:SerializedName("results")
    val places: List<UserResponse>
)


data class UserResponse(
    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("status")
    val status: String,

    @field:SerializedName("image")
    val image: String,



)
