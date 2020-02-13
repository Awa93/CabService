package com.example.cabservice.data.network

import com.example.cabservice.data.network.responses.AuthResponse
import com.example.cabservice.data.network.responses.SignUpResponse
import com.google.gson.JsonObject
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface MyApi {

    @POST(ApiConstant.LOGINAPI)
   suspend fun login(
        @Body jsonObject: JsonObject
    ):Response<AuthResponse>


    @FormUrlEncoded
    @POST("api/Customer/SignupCustomer")
    suspend fun userSignup(
        @Body jsonObject: JsonObject
    ):Call<ResponseBody>


    companion object{
        operator fun invoke(networkConectionIntercepter: NetworkConectionIntercepter):MyApi{

            val okHttpClient=OkHttpClient.Builder()
                .addInterceptor(networkConectionIntercepter)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(ApiConstant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MyApi::class.java)
        }
    }
}

