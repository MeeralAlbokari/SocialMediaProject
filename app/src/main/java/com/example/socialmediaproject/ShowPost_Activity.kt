package com.example.socialmediaproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

    var id=1
    var showcomments= CommentRV()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityShowPostBinding.inflate(layoutInflater)
        setContentView(binding.root)



        addcomment()

        binding.apply {
            /*var comment= commentET.text.toString()
            if (comment.isNotEmpty()){
                addcomment(comment)
            }
            commentbtn.setOnClickListener {


            } //end on click*/
            commentRv.adapter=showcomments

        } //end apply


    } //end create

    fun addcomment(){

        binding.apply {
            var apiclient= APIClient.getClient()
            if (apiclient != null) {

                var apiInter = apiclient.create(APIinterface::class.java)
                apiInter.getpost(id).enqueue(object : Callback<PostsInfoItem> {
                    override fun onResponse(
                        call: Call<PostsInfoItem>,
                        response: Response<PostsInfoItem>
                    ) {

                        var post=response.body()!!

                        //showcomment.setText(posts.comments)
                        showcomments.submitList(listOf(post))

                        showtitle.setText(post.title)
                        showuser.setText(post.user)
                        showcontent.setText(post.text)


                        commentbtn.setOnClickListener {
                            var comment=commentET.text.toString()
                            if(comment.isNotEmpty()){




                            } //end if comment
                        } //end on click

                    }// end response

                    override fun onFailure(call: Call<PostsInfoItem>, t: Throwable) {

                    }


                }) //end obj


            } //if


        } //end apply

    } //end fun



    /*
    fun updateItem(){
       var primk=intent.getIntExtra("pkID", -1)

        binding.apply {

            if(primk !=-1) {

                var apiclient= APICLient().getClient()
                if (apiclient != null){

                    var apiInter= apiclient.create(APIInterface::class.java)
                    apiInter.getCeleb(primk).enqueue(object : Callback<CelebritiesItem>{
                        override fun onResponse(
                            call: Call<CelebritiesItem>,
                            response: Response<CelebritiesItem>
                        ) {

                            var celeb=response.body()!!

                            addongName.setText(celeb.name)
                            addingTaboo1.setText(celeb.taboo1)
                            addingTaboo2.setText(celeb.taboo2)
                            addingTaboo3.setText(celeb.taboo3)

                            update.setOnClickListener {

                                celeb.name=addongName.text.toString()
                                celeb.taboo1=addingTaboo1.text.toString()
                                celeb.taboo2=addingTaboo2.text.toString()
                                celeb.taboo3=addingTaboo3.text.toString()

                                apiInter.updateCeleb(primk, celeb).enqueue(object : Callback<CelebritiesItem>{
                                    override fun onResponse(
                                        call: Call<CelebritiesItem>,
                                        response: Response<CelebritiesItem>
                                    ) {

                                        var backmain = Intent(this@EditCelebrity_Activity, MainActivity::class.java)
                                        startActivity(backmain)
                                        finish()
                                        Toast.makeText(this@EditCelebrity_Activity,"The celebrity has been updated", Toast.LENGTH_LONG).show()
                                    } //end response

                                    override fun onFailure(
                                        call: Call<CelebritiesItem>,
                                        t: Throwable
                                    ) {

                                    } //end f


                                }) //end


                            }// end on click

                        }

                        override fun onFailure(call: Call<CelebritiesItem>, t: Throwable) {

                        }


                    })  //end enqueue
                } //second if
            } //end if
        }//end binding

    } //fun
    */
} //end main