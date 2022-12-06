package com.example.socialmediaproject.Adapter

import androidx.recyclerview.widget.DiffUtil


class CommentDiffUtil (comments:List<String>): DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {

        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {

        return oldItem == newItem

    } //end content


}