package com.maxbt.newsapplication.model.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.maxbt.newsapplication.R
import com.maxbt.newsapplication.databinding.NewsPreviewItemBinding
import com.maxbt.newsapplication.model.entity.News
import com.squareup.picasso.Picasso
import java.time.LocalDate
import java.time.format.DateTimeFormatter

/**
 * Adapter with using binding. for news preview.
 * Containing async Picasso for loading images
 * And diffUtil for loading only parts that were updated due to RecyclerView position changed
 */

class NewsPreviewAdapter : RecyclerView.Adapter<NewsPreviewAdapter.NewsViewHolder>(){
    inner class NewsViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        var binding : NewsPreviewItemBinding =  NewsPreviewItemBinding.bind(itemView)
    }

    val differ = AsyncListDiffer(this, object : DiffUtil.ItemCallback<News>(){

        override fun areItemsTheSame(oldItem: News, newItem: News): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: News, newItem: News): Boolean =
            oldItem == newItem
    })


    var onItemClickListener: ((News) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        //LayoutInflater.inflate -> transform  from layout.view to object View
        val binding = NewsPreviewItemBinding.inflate(inflater, parent, false)

        val view = binding.root


        //Holder is class needed to contain only displayable items and not a whole list
        val holder = NewsViewHolder(view)
        holder.itemView.setOnClickListener { _ ->
            val news = differ.currentList[holder.adapterPosition]
            onItemClickListener?.let { it(news) }
        }

        return holder
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val article  = differ.currentList[position]
        holder.binding.apply {
            Picasso.get()
                .load(article.imageUrl)
                .error(R.drawable.no_image)
                .into(imageViewPreview)
            textViewTitle.text = article.title.title

            val currentDate = LocalDate.parse(article.date, DateTimeFormatter.ISO_LOCAL_DATE_TIME)
            textViewTime.text = String.format("${currentDate.dayOfMonth} ${currentDate.month.name}")
        }
    }
    override fun getItemCount(): Int = differ.currentList.size

}