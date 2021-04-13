package com.example.preparation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class MainActivity : AppCompatActivity() {
    lateinit var mService: PostAPI
    lateinit var layoutManager: LinearLayoutManager
    lateinit var adapter: PostAdapter
    lateinit var mainScroll : RecyclerView
    lateinit var text_field : TextView

    lateinit var mainBtn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainBtn = (findViewById<Button>(R.id.btnMain))
        text_field = findViewById(R.id.text_v)
        mainScroll = findViewById<RecyclerView>(R.id.mainScroll)

        mService = Common.retrofitService
        mainScroll.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(this)
        mainScroll.layoutManager = layoutManager


        mainBtn.setOnClickListener{
            getAllPosts()
            mainBtn.text = "Update"


/*
            val intent = Intent(it.context, PostDetailsActivity::class.java)
            intent.putExtra("id", id_txt.text)
            intent.putExtra("postId", postId_txt.text)
            intent.putExtra("title", title_txt.text)
            intent.putExtra("body", body_txt.text)

            startActivity(intent)
*/
        }


    }

    private fun getAllPosts() {
        mService.getPostList().enqueue(object : Callback<MutableList<Post>> {
            override fun onFailure(call: Call<MutableList<Post>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error happened when getting posts", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<MutableList<Post>>, response: Response<MutableList<Post>>) {
                adapter = PostAdapter(baseContext, response.body() as MutableList<Post>)
                adapter.notifyDataSetChanged()
                mainScroll.adapter = adapter

            }
        })
    }
}