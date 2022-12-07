package com.example.socialmediaproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.socialmediaproject.apiclasses.APIClient
import com.example.socialmediaproject.apiclasses.APIinterface
import com.example.socialmediaproject.databinding.ActivityLogInBinding
import com.example.socialmediaproject.databinding.CommentRowBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LogIn_Activity : AppCompatActivity() {

    lateinit var binding: ActivityLogInBinding


    var user = "az"
    var password = 123

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.apply {



        } //end apply


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


}// end main