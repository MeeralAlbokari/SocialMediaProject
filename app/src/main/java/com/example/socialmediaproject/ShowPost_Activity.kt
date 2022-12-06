package com.example.socialmediaproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.socialmediaproject.Adapter.CommentRV
import com.example.socialmediaproject.JSON.PostsInfoItem
import com.example.socialmediaproject.apiclasses.APIClient
import com.example.socialmediaproject.apiclasses.APIinterface
import com.example.socialmediaproject.databinding.ActivityShowPostBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ShowPost_Activity : AppCompatActivity() {

    lateinit var binding: ActivityShowPostBinding
    lateinit var commentadapter: CommentRV
    lateinit var commentsList:List<String>
    lateinit var post:PostsInfoItem


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityShowPostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        commentsList= listOf()
        commentadapter=CommentRV(commentsList)

        binding.commentRv.adapter=commentadapter


        var primk=intent.getIntExtra("id", 1)
        updatepost(primk)


    } //end create

    fun updatepost(primk:Int){

        binding.apply {
            var apiclient= APIClient.getClient()
            if (apiclient != null) {

                var apiInter = apiclient.create(APIinterface::class.java)

                commentbtn.setOnClickListener {
                    //need to add condition if the user is logged to be able to comment on the post
                    var addcomment = commentET.text.toString()

                    if (addcomment.isNotEmpty()) {

                        post.comments += ", $addcomment"
                        Log.d("TAG", "onResponse: ${addcomment} + ${post.comments} ")
                        commentsList = post.comments.split(",")
                        showcomment.text="Comments: ${commentsList.size}"
                        commentadapter.update(commentsList)




                        apiInter.updatePost(primk, post).enqueue(object : Callback<PostsInfoItem> {
                            override fun onResponse(
                                call: Call<PostsInfoItem>,
                                response: Response<PostsInfoItem>
                            ) {

                            } //end response

                            override fun onFailure(
                                call: Call<PostsInfoItem>,
                                t: Throwable
                            ) {

                            } //end failure
                        }) //end obj
                    } //end if check if empty
                } //on click


                showlike.setOnClickListener {

                    //need to add condition if the user is logged to be able to like the post
                    post.likes += ", "
                    var likes=post.likes.split(",")
                    showlike.text=" Likes: ${likes.size}"


                } //end on click


                apiInter.getpost(primk).enqueue(object : Callback<PostsInfoItem> {
                    override fun onResponse(
                        call: Call<PostsInfoItem>,
                        response: Response<PostsInfoItem>
                    ) {

                         post=response.body()!!

                        Log.d("body", "hi $post" )

                        //print what's in the details of the particular id that has been clicked
                        showtitle.text=post.title
                        showuser.text=post.user
                        showcontent.text=post.text
                        commentsList=post.comments.split(",")
                        showcomment.text="Comments: ${commentsList.size}"
                        //showlike.text=post.likes
                        var likes=post.likes.split(",")
                        showlike.text=" Likes: ${likes.size}"
                       commentadapter.update(commentsList)
                    }// end response

                    override fun onFailure(call: Call<PostsInfoItem>, t: Throwable) {
                        Log.d("body", "hi f" )
                    } //end failure
                }) //end obj
            }//end api if

        } //apply


    } //end fun





    } //end main


