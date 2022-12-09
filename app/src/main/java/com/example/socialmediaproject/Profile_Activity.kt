package com.example.socialmediaproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.example.socialmediaproject.JSON.UserInfoItem
import com.example.socialmediaproject.apiclasses.APIClient
import com.example.socialmediaproject.apiclasses.APIinterface
import com.example.socialmediaproject.databinding.ActivityProfileBinding
import kotlinx.android.synthetic.main.activity_profile.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Profile_Activity : AppCompatActivity() {
    lateinit var binding: ActivityProfileBinding
    //var apiKey = " "


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //username = intent.getStringExtra("userName").toString()
        //apiKey = intent.getStringExtra("apiKey").toString()
       // Log.d("apiKey","'ProfileActiv' i got0 : $apiKey")
        binding.apply {
            var apiKey = intent.getStringExtra("apiKey").toString()
            Log.d("API10","$apiKey")
            fillXml(apiKey)
        }


    }

    fun fillXml(key:String){

        var checkAPI= APIClient.getClient()

        if(checkAPI!=null){
            var apiinter=checkAPI.create(APIinterface::class.java)
            apiinter.getUser(key).enqueue(object: Callback<UserInfoItem> {
                override fun onResponse(call: Call<UserInfoItem>, response: Response<UserInfoItem>) {
                    var userItems=response.body()
                    if (userItems!=null){
                        binding.userUserName.setText(userItems.username.toString())
                        binding.userEmail.setText(userItems.email.toString())
                        binding.passwordTxt.setText(userItems.password.toString())
                        binding.userAbout.setText(userItems.about.toString())
                        binding.imageTxt.setText(userItems.image.toString())
                        glideProfilePic("https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/f/91806943-2382-4f85-b288-a4dec56c91ef/d32a14r-f407db61-d62d-415f-bbe4-65622a61f968.jpg?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1cm46YXBwOjdlMGQxODg5ODIyNjQzNzNhNWYwZDQxNWVhMGQyNmUwIiwiaXNzIjoidXJuOmFwcDo3ZTBkMTg4OTgyMjY0MzczYTVmMGQ0MTVlYTBkMjZlMCIsIm9iaiI6W1t7InBhdGgiOiJcL2ZcLzkxODA2OTQzLTIzODItNGY4NS1iMjg4LWE0ZGVjNTZjOTFlZlwvZDMyYTE0ci1mNDA3ZGI2MS1kNjJkLTQxNWYtYmJlNC02NTYyMmE2MWY5NjguanBnIn1dXSwiYXVkIjpbInVybjpzZXJ2aWNlOmZpbGUuZG93bmxvYWQiXX0.evNfsgxd9v2YzLiNyy86lwZQHepb9zaEj8fmAvN_3is")
                    } //end if

                } //response

                override fun onFailure(call: Call<UserInfoItem>, t: Throwable) {
                } //end failure
            }) //end obj
        }
    }
    fun glideProfilePic(url:String){
        Glide.with(this@Profile_Activity).load(url).into(userImage)
    }
}