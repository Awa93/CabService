package com.example.cabservice.ui.auth

import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel
import com.example.cabservice.data.repositories.UserRepository
import com.example.cabservice.util.ApiException
import com.example.cabservice.util.Cotoutines
import com.example.cabservice.util.NoInternetException
import com.example.vardaandriver.ui.auth.SignupActivity
import com.google.gson.JsonObject

class AuthViewModel(
   private val repository: UserRepository
) : ViewModel() {


    var mobile:String?=null
    var password:String?=null
    var name:String?=null
    var email:String?=null

    var authListner:AuthListner?=null
    var signAuthListner:SignAuthListner?=null

    fun getLoggedInuser()=repository.getUser()

    fun onLoginButtonClicked(view:View){
        authListner?.onStarted()
        if (mobile.isNullOrBlank()||password.isNullOrBlank()){
          authListner?.onFailure("Invalid userId Or password")
//            failed
            return
        }

        val jsonObject=JsonObject()
        jsonObject.addProperty("Email_Id",mobile!!)
        jsonObject.addProperty("Password",password!!)

        Cotoutines.main {
            try {
                val authResponse = repository.userLogin(jsonObject)
                authResponse.userDetail?.let {
                    authListner?.onSuccess(it)
                    repository.saveUser(it)
                    return@main

                }
                authListner?.onFailure(authResponse.Message!!)


            }catch (e:ApiException){
                authListner?.onFailure(e.message!!)
            }catch (e:NoInternetException){
                authListner?.onFailure(e.message!!)
            }


        }
    }

    fun OnForgotPasswaordClicked( view:View){
        authListner?.onStarted()

    }


    fun onSignup(view: View){
        Intent(view.context,SignupActivity::class.java).also {
            view.context.startActivity(it)
        }
    }





    fun onLogin(view: View){
        Intent(view.context,LoginActivity::class.java).also {
            view.context.startActivity(it)
        }
    }

    fun onSignupButtonClicked(view: View){
        signAuthListner?.onStarted()
        if (name.isNullOrBlank()){
            signAuthListner?.onFailure("Please enter name")
            return
        }

        if (email.isNullOrBlank()){
            signAuthListner?.onFailure("Please enter email")
            return
        }

        if (mobile.isNullOrBlank()){
            signAuthListner?.onFailure("Please enter mobile")
            return
        }

        if (password.isNullOrBlank()){
            signAuthListner?.onFailure("Please enter password")
            return
        }

        val jsonObject= JsonObject()
        jsonObject.addProperty("Email_Id",email!!)
        jsonObject.addProperty("Password",password!!)
        jsonObject.addProperty("Phone",mobile!!)
        jsonObject.addProperty("FullName",name!!)

        Cotoutines.main {
            try {
                val signupResponse=repository.userSignup(jsonObject)
                signAuthListner?.onSuccess(signupResponse)



            }catch (e: ApiException){
                signAuthListner?.onFailure(e.message!!)
            }catch (e: NoInternetException){
                signAuthListner?.onFailure(e.message!!)
            }


        }


    }



}