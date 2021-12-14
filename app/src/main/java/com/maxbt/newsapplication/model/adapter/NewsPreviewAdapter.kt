package com.maxbt.newsapplication.model.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.maxbt.newsapplication.databinding.NewsPreviewItemBinding
import com.maxbt.newsapplication.model.entity.News
import com.squareup.picasso.Picasso

/**
 * Adapter with using binding. for news preview.
 * Containing async Picasso for loading images
 * Later there would be click listeners for views
 *
 */

class NewsPreviewAdapter : RecyclerView.Adapter<NewsPreviewAdapter.NewsViewHolder>(){

    //Differ(DiffUtil) is used to update only parts of the recycler views that is changed, and not whole
    //recycler view as it in  standart realization
    //TODO: This would be moved to callback class , when adapters would be more than 1
    private val differCallback = object : DiffUtil.ItemCallback<News>(){

        override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, differCallback)


    //Inner class means that this is class in the class, only for good reading i think
    inner class NewsViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        var binding : NewsPreviewItemBinding =  NewsPreviewItemBinding.bind(itemView)
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = NewsPreviewItemBinding.inflate(inflater, parent, false)
        return NewsViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val news  = differ.currentList[position]

        holder.binding.apply {
            Picasso
                .get()
                .load(news.links.featuredMedia.imageUrl)
                .into(imageViewPreview)
            textViewTitle.text = news.title
            textViewTime.text = news.date

            //TODO: Change int number of category to String get category by int
            textViewCategory.text = news.category.toString()

        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}