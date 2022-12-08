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



    var apiKey = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.apply {
            var userName = usrenameEt.text.toString()
            var password = passET.text.toString()
            Log.d("trace UserName","$userName")
            Log.d("trace Pass","$password")

            // checking if the userName and password are correct
            binding.button2.setOnClickListener {
                if (userName.isNotEmpty()&&password.isNotEmpty()){
                    CheckUserEmail(userName,password)
                    Log.d("trace UserName ACheck","$userName")
                    Log.d("trace Pass After Check","$password")

                    if (apiKey.length>45){
                        var moveToPosts= Intent(this@LogIn_Activity, Posts_Activity::class.java)
                        startActivity(moveToPosts)
                    }
                }

            }



        } //end apply


    } //end create


    fun CheckUserEmail(username:String, password:String){
        val apiClient = APIClient.getClient()
        if (apiClient != null){
            val apiInterface = apiClient.create(APIinterface::class.java)
            apiInterface.logIn(username,password).enqueue(object : Callback<String> {
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    var UsersItem = response.body()
                    if (UsersItem != null){
                        Log.d("trace RespB","${response.body()}")
                        Log.d("trace UserItem","${UsersItem.toString()}")
                        apiKey = UsersItem.toString()

                        // ***************
                        val intentFromLogIn = Intent(this@LogIn_Activity,Posts_Activity::class.java)
                        //intentPost.putExtra("userName",username)
                        intentFromLogIn.putExtra("userName",username)
                        Log.d("AABB", "$username")
                        startActivity(intentFromLogIn)

                        //****************
                    } //end if

                } //end response

                override fun onFailure(call: Call<String>, t: Throwable) {
                    Log.d("Tag proplem","${t.message}")
                } //end failure


            }) // End the object call
        } //end if
    } //end fun



}// end main