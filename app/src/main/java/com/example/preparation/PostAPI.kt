package com.example.preparation

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

data class Post (var userId : Int, var id : Int, var title : String, var body : String) {}

data class Result (val total_count: Int, val incomplete_results: Boolean, val items: List<Post>)

object RetrofitClient {
    private var retrofit: Retrofit? = null

    fun getClient(baseUrl: String): Retrofit {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit!!
    }
}

interface PostAPI {

    @GET("/posts")
    fun getPostList() : Call<MutableList<Post>>
}