package com.latihan.mandirinews.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.latihan.mandirinews.R
import com.latihan.mandirinews.databinding.AllNewsBinding
import com.latihan.mandirinews.models.AllNewsResponse
import com.latihan.mandirinews.models.HeadlineNewsResponse

class AllNewsAdapter: RecyclerView.Adapter<AllNewsAdapter.AllNewsAdapterViewHolder>() {

    private var allNews: List<AllNewsResponse.ArticlesItem?>? = listOf()

    class AllNewsAdapterViewHolder(
        private var binding: AllNewsBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: AllNewsResponse.ArticlesItem) {
            binding.apply {
                Glide.with(ivAllNews)
                    .load(data.urlImage)
                    .error(R.drawable.ic_launcher_background)
                    .into(ivAllNews)
                tvAllNewsTitle.text = data.title
                tvAllNewsAuthor.text = data.source!!.name
                tvAllNewsDate.text = data.publishedAt
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllNewsAdapterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = AllNewsBinding.inflate(layoutInflater, parent, false)
        return AllNewsAdapterViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return allNews!!.size
    }

    override fun onBindViewHolder(holder: AllNewsAdapterViewHolder, position: Int) {
        holder.bind(allNews!![position]!!)
    }

    fun setData(data: List<AllNewsResponse.ArticlesItem?>?) {
        allNews = data
    }
}