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

            // checking if the userName and password are correct
            CheckUserEmail(userName,password)


        } //end apply


    } //end create


    fun CheckUserEmail(username:String, password:String){
        val apiClient = APIClient.getClient()
        if (apiClient != null){
            val apiInterface = apiClient.create(APIinterface::class.java)
            apiInterface.logIn(username,password).enqueue(object : Callback<String> {
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    var UsersItem = response.body()
                    //Log.d("Tagg","${UsersItem.toString()}")
                    //apiKey = UsersItem.toString()
                    if (UsersItem != null){
                        Log.d("Tag","${UsersItem}")
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