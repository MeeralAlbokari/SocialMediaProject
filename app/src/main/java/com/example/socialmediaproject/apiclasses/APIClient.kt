package com.example.socialmediaproject.apiclasses

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIClient {

    var retrofitvar: Retrofit? =null

    fun getClient(): Retrofit?{

        retrofitvar = Retrofit.Builder()
            .baseUrl("https://apidojo.pythonanywhere.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofitvar

    }// fun get


}// end