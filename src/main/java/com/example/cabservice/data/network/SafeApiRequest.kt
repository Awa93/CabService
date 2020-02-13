package com.example.cabservice.data.network

import com.example.cabservice.util.ApiException
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response

abstract class SafeApiRequest{

    suspend fun <T:Any> apiRequest(call:suspend()->Response<T>):T{
        val response=call.invoke()

        if (response.isSuccessful){
            return response.body()!!
        }else{
            val err=response.errorBody()?.string()
            val message=StringBuilder()
            err?.let {
                try{
                    message.append(JSONObject(it).getString("Message"))


                }catch (e:JSONException){
                    message.append("\n")
                }

            }
            message.append("Error code ${response.code()}")

            throw ApiException(message.toString())
        }
    }

}