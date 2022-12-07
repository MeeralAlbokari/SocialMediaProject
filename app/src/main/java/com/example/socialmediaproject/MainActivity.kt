package com.example.socialmediaproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.socialmediaproject.Adapter.SocialRV
import com.example.socialmediaproject.JSON.PostsInfo
import com.example.socialmediaproject.apiclasses.APIClient
import com.example.socialmediaproject.apiclasses.APIinterface
import com.example.socialmediaproject.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    var user = "az"
    var password = 123


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        /*binding.loginbtn.setOnClickListener{
            CheckUserEmail(user,password)
        }




        binding.createaccbtn.setOnClickListener {

        }

        binding.apply {

        } //end*/

        } //end

    } //end create

    fun CheckUserEmail(user:String, password:Int){
        val apiClient = APIClient.getClient()
        if (apiClient != null){
            val apiInterface = apiClient.create(APIinterface::class.java)
            apiInterface.logIn(user,password).enqueue(object : Callback<String> {
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    var UsersItem = response.body()

                    Log.d("Tagg","${UsersItem.toString()}")

                    if (UsersItem != null){
                        Log.d("Tag","${UsersItem}")
                    } //end if

                } //end response

                override fun onFailure(call: Call<String>, t: Throwable) {
                    Log.d("Tag proplem","${t.message}")
                } //end failure


            }) // End the object call
        } //end if
    } //end fun


//end main