package com.example.socialmediaproject.Adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.socialmediaproject.JSON.PostsInfoItem

class CommentDiffUtil (): DiffUtil.ItemCallback<PostsInfoItem>() {
    override fun areItemsTheSame(oldItem: PostsInfoItem, newItem: PostsInfoItem): Boolean {

        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: PostsInfoItem, newItem: PostsInfoItem): Boolean {

        return oldItem == newItem

    } //end content


}