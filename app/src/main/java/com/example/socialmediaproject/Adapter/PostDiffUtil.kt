package com.example.socialmediaproject.Adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.socialmediaproject.JSON.PostsInfoItem


class PostDiffUtil (): DiffUtil.ItemCallback<PostsInfoItem>() {
    override fun areItemsTheSame(oldItem: PostsInfoItem, newItem: PostsInfoItem): Boolean {

        return oldItem.id ==newItem.id
    }

    override fun areContentsTheSame(oldItem: PostsInfoItem, newItem: PostsInfoItem): Boolean {

        return when{
            oldItem.id== newItem.id -> true
            oldItem.user == newItem.user -> true
            oldItem.text == newItem.text -> true
            else-> (false)
        } //when

    } //end content


}