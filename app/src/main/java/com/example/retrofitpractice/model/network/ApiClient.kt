package com.example.retrofitpractice.model.network

import com.example.retrofitpractice.model.responses.Posts
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface ApiClient {

    @GET("/posts")
    fun getAllPosts(): Call<List<Posts>>
}