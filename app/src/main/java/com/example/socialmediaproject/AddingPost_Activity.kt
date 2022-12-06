package com.example.socialmediaproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.socialmediaproject.JSON.PostsInfoItem
import com.example.socialmediaproject.apiclasses.APIClient
import com.example.socialmediaproject.apiclasses.APIinterface
import com.example.socialmediaproject.databinding.ActivityAddingPostBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddingPost_Activity : AppCompatActivity() {

    lateinit var binding: ActivityAddingPostBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityAddingPostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addpost.setOnClickListener {
            addingpost()
        }//end




    } //end create


    fun addingpost(){

        binding.apply {
            var title=titleET.text
            var content=contentET.text

            if (title.isNotEmpty()&& content.isNotEmpty()){

                var checkAPI= APIClient.getClient()
                if(checkAPI !=null){
                    var apiInter= checkAPI.create(APIinterface::class.java)
                    apiInter.addPost(
                        PostsInfoItem(
                            0,
                            binding.titleET.text.toString(),
                            user = "", //change to the username from the main
                            binding.contentET.text.toString(),
                            "",
                            ""

                        )
                    ).enqueue(object: Callback<PostsInfoItem> {
                        override fun onResponse(
                            call: Call<PostsInfoItem>,
                            response: Response<PostsInfoItem>
                        ) {
                            var moveActivity = Intent(this@AddingPost_Activity, Posts_Activity::class.java)
                            startActivity(moveActivity)
                            finish()
                        } //response


                        override fun onFailure(call: Call<PostsInfoItem>, t: Throwable) {

                        } //failure

                    })//object
                } //if second
            } //first if
        } //end binding
    } //fun


} //end main