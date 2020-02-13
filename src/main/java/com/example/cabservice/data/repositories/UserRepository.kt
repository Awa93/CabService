package com.example.cabservice.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cabservice.data.appdb.AppDatabase
import com.example.cabservice.data.appdb.entity.User_Detail
import com.example.cabservice.data.network.MyApi
import com.example.cabservice.data.network.SafeApiRequest
import com.example.cabservice.data.network.responses.AuthResponse
import com.google.gson.JsonObject
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository(
    private val api: MyApi,
    private val db:AppDatabase
):SafeApiRequest() {

    suspend fun userLogin(jsonObject: JsonObject): AuthResponse {
        return apiRequest { api.login(jsonObject) }
    }

    suspend fun userSignup(jsonObject: JsonObject):LiveData<String>{
        val signUpResponse=MutableLiveData<String>()

        api.userSignup(jsonObject)
            .enqueue(object: Callback<ResponseBody>{
                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    signUpResponse.value=t.message
                }

                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>) {
                    if (response.isSuccessful){
                        signUpResponse.value=response.body()?.string()
                    }else{
                        signUpResponse.value=response.errorBody()?.string()

                    }

                }

            })
        return signUpResponse


    }

    suspend fun saveUser(userDetail: User_Detail)=db.getUserDao().insertUser(userDetail)

    fun getUser()=db.getUserDao().getUser()




}