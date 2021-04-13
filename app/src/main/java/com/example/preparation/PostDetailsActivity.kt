package com.example.preparation

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class PostDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_details)

        Log.w("PostDetaisAcivity", "onCreate")

        val post : Post = Post(getIntent().getStringExtra("id")!!.toInt(),
                                getIntent().getStringExtra("postId")!!.toInt(),
                                getIntent().getStringExtra("title")!!,
                                getIntent().getStringExtra("body")!!)

        (findViewById<TextView>(R.id.llt1)).text = "id : "
        (findViewById<TextView>(R.id.llt2)).text = post.userId.toString()

        (findViewById<TextView>(R.id.llt3)).text = "postId : "
        (findViewById<TextView>(R.id.llt4)).text = post.id.toString()

        (findViewById<TextView>(R.id.llt5)).text = "title : "
        (findViewById<TextView>(R.id.llt2)).text = post.title

        (findViewById<TextView>(R.id.llt7)).text = "body : "
        (findViewById<TextView>(R.id.llt2)).text = post.body



    }
}