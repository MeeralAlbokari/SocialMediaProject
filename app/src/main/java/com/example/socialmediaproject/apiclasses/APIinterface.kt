package com.example.socialmediaproject.apiclasses

import com.example.socialmediaproject.JSON.PostsInfo
import com.example.socialmediaproject.JSON.PostsInfoItem
import com.example.socialmediaproject.JSON.UserInfo
import com.example.socialmediaproject.JSON.UserInfoItem
import retrofit2.Call
import retrofit2.http.*

interface APIinterface {


    //to fetch the posts and update the list, comments, likes ..etc

    @GET("posts/")
    fun getall(): Call<PostsInfo>

    @GET("posts/")
    fun getpost(@Path("postID") postID: Int): Call<PostsInfoItem>

    @POST("posts/")
    fun addPost(@Body postData: PostsInfoItem): Call<PostsInfoItem>

    @PUT("posts/{postId}")
    fun updatePost(@Path("postID") postId: Int, @Body updateData: PostsInfoItem): Call<PostsInfoItem>









    /*@GET("users/")
    suspend fun getAllUsers(): Call<UserInfo>

    @POST("users/")
    fun addUser(@Body userData: UserInfoItem): Call<UserInfoItem>

    @GET("login/{username}/{password}")
    suspend fun logIn(@Path("username") username: String, @Path("password") password: String): Call<String>

    @GET("users/{apiKey}")
    suspend fun getUser(@Path("apiKey") apiKey: String): Call<UserInfoItem>*/


}