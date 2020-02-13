package com.example.cabservice.ui.auth
import com.example.cabservice.data.appdb.entity.User_Detail
interface AuthListner {

    fun onStarted()
    fun onSuccess(userDetail: User_Detail)
    fun onFailure(msg:String)
}