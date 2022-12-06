package com.example.socialmediaproject.Adapter


import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.socialmediaproject.JSON.PostsInfoItem
import com.example.socialmediaproject.Posts_Activity
import com.example.socialmediaproject.ShowPost_Activity
import com.example.socialmediaproject.databinding.PostRowBinding


class SocialRV (val activity:Posts_Activity): ListAdapter<PostsInfoItem, SocialRV.ViewHolder>(PostDiffUtil()) {

    class ViewHolder(var binding: PostRowBinding): RecyclerView.ViewHolder(binding.root){
    } //end item

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(PostRowBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var post= getItem(position)

        holder.binding.apply {

            showtitle.text=post.title.toString()
            showuser.text=post.user.toString()
            showcontent.text=post.text.toString()


            /*postCV.setOnClickListener{
                var moveActivity= Intent(context,ShowPost_Activity::class.java)
                moveActivity.putExtra("id",post.id)
                context.startActivity(moveActivity)

            } //end set onclick*/
            postCV.setOnClickListener{

                activity.showpost(post.id)


            }



        } //apply

    } // on bindview






}