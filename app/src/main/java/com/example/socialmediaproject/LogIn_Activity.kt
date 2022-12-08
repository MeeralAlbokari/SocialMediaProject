package com.example.socialmediaproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.socialmediaproject.apiclasses.APIClient
import com.example.socialmediaproject.apiclasses.APIinterface
import com.example.socialmediaproject.databinding.ActivityLogInBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LogIn_Activity : AppCompatActivity() {

    lateinit var binding: ActivityLogInBinding
    var apiKey = " "





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.apply {



            // checking if the userName and password are correct
            binding.button2.setOnClickListener {
                var userName = usrenameEt.text.toString()
                var password = passET.text.toString()
                Log.d("trace UserName","$userName")
                Log.d("trace Pass","$password")
                if (userName.isNotEmpty()&&password.isNotEmpty()){
                    LogIn(userName,password)
                    Log.d("trace UserName ACheck","$userName")
                    Log.d("trace Pass After Check","$password")
                    Log.d("trace KeyLen1","$apiKey")
                }
            }



        } //end apply


    } //end create


    fun LogIn(user:String, password:String){
        val apiClient = APIClient.getClient()
        if (apiClient != null){
            val apiInterface = apiClient.create(APIinterface::class.java)
            apiInterface.logIn(user,password).enqueue(object : Callback<String> {
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    var UsersItem = response.body()
                    Log.d("Tagg","${UsersItem.toString()}")
                    if (UsersItem != null){
                        Log.d("TagV","${UsersItem}")
                        Log.d("TagR","${response.body()}")
                        apiKey = UsersItem
                        Log.d("TagR","Email: $apiKey")
                        if (apiKey.length>45){
                            Log.d("trace KeyLen2","$apiKey")
                            var moveToPosts= Intent(this@LogIn_Activity, Posts_Activity::class.java)
                            moveToPosts.putExtra("userName",user)
                            startActivity(moveToPosts)
                            //
                        }
                    }
                }

                override fun onFailure(call: Call<String>, t: Throwable) {
                    Log.d("Tag proplem","${t.message}")
                }


            }) // End the object call
        }
    } //end fun



}// end main