package com.example.flickrbrowserapp

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_row.view.*
import androidx.core.content.ContextCompat.startActivity


class RVAdapter(private var list: List<Photo>): RecyclerView.Adapter<RVAdapter.ItemViewHolder>() {
    class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_row, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = list[position]

        holder.itemView.apply {
            title.text = item.title
            OurClass.mainActivity.updateImageByURL(FlickrAPI.getPhotoURL(item, "w"), imageView)

            imageView.setOnClickListener {
                OurClass.imageViewURL = FlickrAPI.getPhotoURL(item, "w")
                val intent = Intent(OurClass.mainActivity, ImageActivity::class.java)
                OurClass.mainActivity.startActivity(intent)
            }
        }
    }

    override fun getItemCount() = list.size
}