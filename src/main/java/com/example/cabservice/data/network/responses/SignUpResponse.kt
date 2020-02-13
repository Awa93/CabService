package com.example.cabservice.data.network.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



data class SignUpResponse (
    var Message: String?,
    var Status: String?,
    var User_Id: Int?,
    var Phone: String? ,
    var Email_Id: String? ,
    var FullName: String? ,
    var Password: String? = null
)