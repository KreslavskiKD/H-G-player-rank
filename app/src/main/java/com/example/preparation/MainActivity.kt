package com.example.preparation

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




/*
    fun run(url: String) {
        progress.visibility = View.VISIBLE
        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                progress.visibility = View.GONE
            }

            override fun onResponse(call: Call, response: Response) {
                var str_response = response.body()!!.string()
                //creating json object
                val json_contact:JSONObject = JSONObject(str_response)
                //creating json array
                var jsonarray_info:JSONArray = json_contact.getJSONArray("info")

                var size:Int = jsonarray_info.length()
                arrayList_details = ArrayList();
                for (i in 0.. size-1) {
                    var json_objectdetail:JSONObject=jsonarray_info.getJSONObject(i)
                    var model : Post = Post(json_objectdetail.getInt("userId"),
                        json_objectdetail.getInt("id"),
                        json_objectdetail.getString("title"),
                        json_objectdetail.getString("body")
                    )
                    arrayList_details.add(model)
                }

                runOnUiThread {
                    //stuff that updates ui
                    arrayList_details.forEach {
                        addFragment(it)
                    }
                }
                progress.visibility = View.GONE
            }
        })
    }*/
}