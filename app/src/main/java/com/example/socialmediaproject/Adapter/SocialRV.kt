package com.example.socialmediaproject.Adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.socialmediaproject.JSON.PostsInfoItem
import com.example.socialmediaproject.Posts_Activity
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

            //Here it checks if the comments and likes aren't empty to print their size's in the textView
            if(post.comments.isNotEmpty()){
                showcom.text = "Comment: ${post.comments.split(",").size} "
            } //end if comments
            if(post.likes.isNotEmpty()){
                showlikes.text = "Likes: ${post.likes.split(",").size} "
            } //end likes


            //whenever the textView of the thread is clicked it will pass the id of the post and change activity to show details
            thread.setOnClickListener{

                activity.showpost(post.id)
            } //end on click



        } //apply

    } // on bindview






} //end RV