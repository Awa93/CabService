package com.example.cabservice.data.network.responses

import com.example.cabservice.data.appdb.entity.User_Detail
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class AuthResponse(
    var Status:Int,
    var Message:String?,

    @SerializedName("User_Detail")
    @Expose
    var userDetail: User_Detail?
)