package com.example.socialmediaproject.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.socialmediaproject.JSON.PostsInfoItem
import com.example.socialmediaproject.databinding.CommentRowBinding


class CommentRV (): ListAdapter<PostsInfoItem, CommentRV.ViewHolder>(CommentDiffUtil()) {

    class ViewHolder(var binding: CommentRowBinding): RecyclerView.ViewHolder(binding.root){
    } //end item

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            CommentRowBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var comments= getItem(position)

        holder.binding.apply {

            userTv.text=comments.user.toString()
            commentTV.text=comments.comments

        } //apply

    } // on bindview


}