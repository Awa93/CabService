package com.example.cabservice.ui.auth

import androidx.lifecycle.LiveData

interface SignAuthListner {

    fun onStarted()
    fun onSuccess(signupResponse: LiveData<String>)
    fun onFailure(msg:String)
}