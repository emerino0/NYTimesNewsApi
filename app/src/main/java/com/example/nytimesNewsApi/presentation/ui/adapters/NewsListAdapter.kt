package com.example.nytimesNewsApi.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nytimesNewsApi.data.models.NewsArticle
import com.example.nytimesNewsApi.databinding.LayoutNewsItemBinding
import com.example.nytimesNewsApi.presentation.ui.fragments.NewsListFragmentDirections

class NewsListAdapter : RecyclerView.Adapter<NewsListAdapter.NewsListViewHolder>() {

    private var binding: LayoutNewsItemBinding? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsListViewHolder {
        binding = LayoutNewsItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return NewsListViewHolder(binding!!)
    }

    override fun onBindViewHolder(holder: NewsListViewHolder, position: Int) {

        val currentNews = differ.currentList[position]

        holder.itemView.apply {

            Glide.with(this)
                .load(currentNews.media.firstOrNull()?.mediaMetaData?.firstOrNull()?.url)
                .into(binding?.ivArticleImage!!)

            binding?.tvNewsDate?.text = currentNews.publishedDate
            binding?.tvNewsTitle?.text = currentNews.title
            binding?.tvNewsAuthor?.text = currentNews.byline
            binding?.tvNewsSection?.text = currentNews.section

        }.setOnClickListener{
            val direction = NewsListFragmentDirections
                .actionNewsListFragmentToNewsDetailFragment(currentNews)
            it.findNavController().navigate(direction)
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    private val differCallback = object :
        DiffUtil.ItemCallback<NewsArticle>() {

        override fun areItemsTheSame(oldItem: NewsArticle, newItem: NewsArticle): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: NewsArticle, newItem: NewsArticle): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)


    class NewsListViewHolder(itemBinding: LayoutNewsItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

}