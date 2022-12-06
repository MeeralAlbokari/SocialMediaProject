package com.example.socialmediaproject.Adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.socialmediaproject.JSON.PostsInfoItem
import com.example.socialmediaproject.databinding.CommentRowBinding


class CommentRV (var splitList: List<String>): RecyclerView.Adapter<CommentRV.ViewHolder>() {

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



        var comment=splitList[position]
        holder.binding.apply {

            if (comment!=""){
                commentTV.text=comment
            } //end if comment

            Log.d("TAG", "onBindViewHolder: ${comment}")


        } //apply

    } // on bindview

    override fun getItemCount()=splitList.size



    fun update(newList: List<String>){
        splitList=newList
        notifyDataSetChanged()
    } //end fun


} //end RV