package com.example.socialmediaproject

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.socialmediaproject.JSON.UserInfo
import com.example.socialmediaproject.JSON.UserInfoItem
import com.example.socialmediaproject.apiclasses.APIClient
import com.example.socialmediaproject.apiclasses.APIinterface
import com.example.socialmediaproject.databinding.ActivitySignUpBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDateTime

class SignUp_Activity : AppCompatActivity() {
    lateinit var binding : ActivitySignUpBinding
    lateinit var userlsList : ArrayList<String>
    //var user = "az"
    lateinit var postsItem: UserInfoItem
    var password = 123
    var x = true
    var y = true
    lateinit var createdAt :String


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.signUpBtn.setOnClickListener{
            binding.apply {
                var email = signEmail.text.toString()
                var username = signUserName.text.toString()
                var password = signpass.text.toString()
                var checkPassword = signpass2.text.toString()
                var about = signAbout.text.toString()
                // image + // wepsite
                var createdAt = LocalDateTime.now().toString()
                if (email.isNotEmpty()&&username.isNotEmpty() &&password.isNotEmpty()&&checkPassword.isNotEmpty()&&about.isNotEmpty()){
                    if (password.length < 65 &&password== checkPassword){
                        SignUp(username,email)
                        if (checkEmailAndUser(x,y)){
                            var user = UserInfoItem(about,createdAt,email," ",password," ",username," ")
                            PutFunc(user)
                        }

                    }
                }

            }

        }


      /*  binding.signUpBtn.setOnClickListener{
            SignUp("abc","abc")
            if (checkEmailAndUser(x,y)){
                putFunc()

            }

        }
        userlsList = ArrayList()

    }*/

}
    fun SignUp(username:String,email: String){
        val apiClient = APIClient.getClient()
        if (apiClient != null){
            val apiInterface = apiClient.create(APIinterface::class.java)
            apiInterface.getUsers().enqueue(object : Callback<UserInfo> {
                override fun onResponse(call: Call<UserInfo>, response: Response<UserInfo>) {
                    val users = response.body()
                    users?.let {
                        for (post in it){
                            var emailsList = arrayListOf<String>()
                            var usernamesList = arrayListOf<String>()
                            emailsList.add(post.email)
                            usernamesList.add(post.username)
                            Log.d("Tag1","${emailsList}")
                            var testEmail = email
                            var testUsername = username
                            for (test in emailsList){
                                if (testEmail == test){
                                    x = false
                                    Toast.makeText(applicationContext, "Bad Email", Toast.LENGTH_LONG).show()
                                    Log.d("Tag123","Positive ${emailsList}")
                                }
                            }
                            for (test in usernamesList) {
                                if (testUsername == test) {
                                    Toast.makeText(applicationContext, "Bad User", Toast.LENGTH_LONG).show()
                                    y = false
                                    Log.d("Tag123", "Positive ${emailsList}")
                                }
                            }
                        }
                    }



                }
                //var usersBody = response.body()


                override fun onFailure(call: Call<UserInfo>, t: Throwable) {
                    Log.d("Tag proplem","${t.message}")
                }


            })
        }
    }
    fun checkEmailAndUser(email: Boolean ,username: Boolean):Boolean {
        var boolean = true

        if (email==true&& username == true){
            boolean = true



        }else{
            boolean = false
            //Toast.makeText(this,"UserName is Already Used",Toast.LENGTH_SHORT).show()

        }
        return boolean
    }

    fun PutFunc(user:UserInfoItem){

        val apiClientFS = APIClient.getClient()
        if (apiClientFS != null) {
            val apiInterfaceFS = apiClientFS.create(APIinterface::class.java)
            if (user != null) {
                apiInterfaceFS.CreateUser(user).enqueue(object : Callback<UserInfoItem> {
                    override fun onResponse(
                        call: Call<UserInfoItem>,
                        response: Response<UserInfoItem>
                    ) {
                        var PostUser = response.body()
                        if (PostUser != null) {
                            PostUser.about= user.about
                            PostUser.username = user.username
                            PostUser.email = user.email
                            PostUser.image = user.image
                            PostUser.password = user.password
                            PostUser.sevarttings = user.sevarttings
                            var moveActivity= Intent(this@SignUp_Activity, MainActivity::class.java)
                            startActivity(moveActivity)
                         //
                        }

                    }
                    override fun onFailure(call: Call<UserInfoItem>, t: Throwable) {
                        Log.d("Tag", "${t.message}")
                    }
                })
            }
            // End the object call

        }
    }
}