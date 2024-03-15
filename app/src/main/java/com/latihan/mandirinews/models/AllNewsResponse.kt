package com.latihan.mandirinews.models

import com.google.gson.annotations.SerializedName

data class AllNewsResponse (
    @field:SerializedName("status")
    val status: String? = null,

    @field:SerializedName("totalResult")
    val totalResult: Int? = null,

    @field:SerializedName("articles")
    val articles: List<ArticlesItem?>? = null
) {
    data class Source (
        @field:SerializedName("id")
        val id: String? = null,

        @field:SerializedName("name")
        val name: String? = null
    )

    data class ArticlesItem (
        @field:SerializedName("author")
        val author: String? = null,

        @field:SerializedName("title")
        val title: String? = null,

        @field:SerializedName("description")
        val description: String? = null,

        @field:SerializedName("url")
        val url: Any? = null,

        @field:SerializedName("urlToImage")
        val urlToImage: Any? = null,

        @field:SerializedName("publishedAt")
        val publishedAt: String? = null,

        @field:SerializedName("content")
        val content: String? = null,

        @field:SerializedName("source")
        val source: Source? = null
    )
}