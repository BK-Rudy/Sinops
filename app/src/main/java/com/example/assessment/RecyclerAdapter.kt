package com.example.assessment

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.example.assessment.databinding.RowItemListBinding
import com.google.gson.Gson
import android.graphics.drawable.Drawable
import android.os.StrictMode
import java.io.InputStream
import java.net.URL


class RecyclerAdapter(list: MutableList<Movie>): RecyclerView.Adapter<RecyclerAdapter.ViewHolder>(){
    private val items: MutableList<Movie> = list
    //class MyViewHolder (val binding: RowItemListBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
        //val v = LayoutInflater.from(parent.context).inflate(R.layout.row_item_list, parent, false)
        val context = LayoutInflater.from(parent.context)
        val v = RowItemListBinding.inflate( context, parent, false)
        return ViewHolder(v)
        //MyViewHolder()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        val `is`: InputStream = URL(item.image).getContent() as InputStream
        val draw = Drawable.createFromStream(`is`, "poster")

        holder.binding.image.setImageDrawable(draw)

        holder.binding.title.text = item.title
        holder.binding.subtitle.text = item.subtitle
        holder.binding.releaseDateText.text = item.releaseDate
        holder.binding.scoreDesc.text = item.rating

        holder.itemView.setOnClickListener {
            it.context.startActivity(
                Intent(
                    it.context,
                    ActivityDetailMovie::class.java
                ).putExtra(Constant.DATA, Gson().toJson(item))
            )
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(val binding: RowItemListBinding): RecyclerView.ViewHolder(binding.root)

}