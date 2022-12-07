package com.example.socialmediaproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.socialmediaproject.Adapter.SocialRV
import com.example.socialmediaproject.JSON.PostsInfo
import com.example.socialmediaproject.apiclasses.APIClient
import com.example.socialmediaproject.apiclasses.APIinterface
import com.example.socialmediaproject.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.apply {


        } //end apply


    } //end create



} //end main