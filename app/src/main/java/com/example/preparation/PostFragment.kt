package com.example.preparation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView


class PostAdapter(private val context: Context, private val postList: MutableList<Post>):
    RecyclerView.Adapter<PostAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val id_txt: TextView = itemView.findViewById(R.id.userId)
        val postId_txt: TextView = itemView.findViewById(R.id.postId)
        val title_txt: TextView = itemView.findViewById(R.id.title)
        val body_txt: TextView = itemView.findViewById(R.id.body)

        fun bind(listItem: Post) {
            itemView.setOnClickListener {
                Toast.makeText(it.context, "pressed post with id = ${itemView.findViewById<TextView>(R.id.postId).text}", Toast.LENGTH_SHORT).show()
                Log.w("PostAdapter", "bind")
                val intent = Intent(it.context, PostDetailsActivity::class.java)
                intent.putExtra("id", id_txt.text)
                intent.putExtra("postId", postId_txt.text)
                intent.putExtra("title", title_txt.text)
                intent.putExtra("body", body_txt.text)
                it.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.post_fragment, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount() = postList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val listItem = postList[position]
        holder.bind(listItem)

        holder.id_txt.text = postList[position].userId.toString()
        holder.postId_txt.text = postList[position].id.toString()
        holder.title_txt.text = postList[position].title
        holder.body_txt.text = postList[position].body
    }

}

