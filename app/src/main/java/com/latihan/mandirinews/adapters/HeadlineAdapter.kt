package com.latihan.mandirinews.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.latihan.mandirinews.R
import com.latihan.mandirinews.databinding.HeadlineNewsBinding
import com.latihan.mandirinews.models.HeadlineNewsResponse

class HeadlineAdapter: RecyclerView.Adapter<HeadlineAdapter.HeadlineAdapterViewHolder>() {

    private var headlineNews: List<HeadlineNewsResponse.ArticlesItem?>? = listOf()

    class HeadlineAdapterViewHolder(
        private var binding: HeadlineNewsBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: HeadlineNewsResponse.ArticlesItem) {
            binding.apply {
                Glide.with(ivHeadline)
                    .load(data.urlToImage)
                    .error(R.drawable.ic_launcher_background)
                    .into(ivHeadline)
                tvHeadlineTitle.text = data.title
                tvHeadlineAuthor.text = data.source!!.name
                tvHeadlineDate.text = data.publishedAt
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeadlineAdapterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = HeadlineNewsBinding.inflate(layoutInflater, parent, false)
        return HeadlineAdapterViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return headlineNews!!.size
    }

    override fun onBindViewHolder(holder: HeadlineAdapterViewHolder, position: Int) {
        holder.bind(headlineNews!![position]!!)
    }

    fun setData(data: List<HeadlineNewsResponse.ArticlesItem?>?) {
        headlineNews = data
    }

}