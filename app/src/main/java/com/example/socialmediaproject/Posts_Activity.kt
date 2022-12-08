package com.example.socialmediaproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.socialmediaproject.Adapter.SocialRV
import com.example.socialmediaproject.JSON.PostsInfo
import com.example.socialmediaproject.JSON.PostsInfoItem
import com.example.socialmediaproject.apiclasses.APIClient
import com.example.socialmediaproject.apiclasses.APIinterface
import com.example.socialmediaproject.databinding.ActivityPostsBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Posts_Activity : AppCompatActivity() {

    lateinit var binding: ActivityPostsBinding

    var showpost = SocialRV(this)
    //var username = " "
    var apiKey = " "
    // user name

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityPostsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        var username = intent.getStringExtra("userName").toString()
        apiKey = intent.getStringExtra("apiKey").toString()
        Log.d("username: ","$username")


        var checkAPI= APIClient.getClient()
        if(checkAPI!=null){
            var apiinter=checkAPI.create(APIinterface::class.java)
            apiinter.getall().enqueue(object: Callback<PostsInfo> {
                override fun onResponse(call: Call<PostsInfo>, response: Response<PostsInfo>) {

                    var posts=response.body()
                    if (posts!=null){

                        //initialize with the items in the array
                        showpost.submitList(posts)

                    } //end if
                } //response

                override fun onFailure(call: Call<PostsInfo>, t: Throwable) {
                } //end failure
            }) //end obj
        } //end if




        binding.apply {

            postsRV.adapter=showpost

            addingbtn.setOnClickListener {

                //when the button is clicked to add post, it will change the activity
                var moveToPostActivity= Intent(this@Posts_Activity, AddingPost_Activity::class.java)
                moveToPostActivity.putExtra("username",username)
                startActivity(moveToPostActivity)

            } //end on click
            showProfile.setOnClickListener {
                var moveToProfile= Intent(this@Posts_Activity, Profile_Activity::class.java)
                moveToProfile.putExtra("apiKey",apiKey)
                Log.d("apiKey","i got : $apiKey")
                startActivity(moveToProfile)
            }

        } //end apply



    } //end create


    //functions to get the id of the post and use it when the textView is clicked
    fun showpost(id: Int ){

        var move=Intent(this, ShowPost_Activity::class.java)
        move.putExtra("id",id)
        startActivity(move)

    } //end
    /*
    sign Up > LogIn + username + api key> posts > >
     */

} //end main