package com.example.preparation

object Common {
    private val BASE_URL = "https://jsonplaceholder.typicode.com/"
    val retrofitService: PostAPI
        get() = RetrofitClient.getClient(BASE_URL).create(PostAPI::class.java)
}