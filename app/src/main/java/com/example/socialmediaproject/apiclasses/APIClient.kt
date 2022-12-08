package com.example.socialmediaproject.apiclasses

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIClient {

    var retrofitvar: Retrofit? =null

    fun getClient(): Retrofit?{

        val gson = GsonBuilder()
            .setLenient()
            .create()

        retrofitvar = Retrofit.Builder()

            .baseUrl("https://apidojo.pythonanywhere.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        return retrofitvar

    }// fun get


}// end