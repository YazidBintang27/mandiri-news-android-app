package com.latihan.mandirinews.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.latihan.mandirinews.R
import com.latihan.mandirinews.adapters.AllNewsAdapter
import com.latihan.mandirinews.adapters.HeadlineAdapter
import com.latihan.mandirinews.databinding.ActivityMainBinding
import com.latihan.mandirinews.ui.viewmodel.MainViewModel
import com.latihan.mandirinews.util.AppConstant

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModels: MainViewModel by viewModels()
    private val headlineNewsAdapter by lazy { HeadlineAdapter() }
    private val allNewsAdapter by lazy { AllNewsAdapter() }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupListenerHeadlineNews()
        setupListenerAllNews()
        observeHeadlineNews()
        observeAllNews()
    }

    private fun setupListenerHeadlineNews() {
        mainViewModels.requestHeadlineNews(
            AppConstant.Country,
            AppConstant.API_KEY
        )
        mainViewModels.status.observe(this@MainActivity, Observer { status ->
            mainViewModels.status.value = null
            Toast.makeText(this@MainActivity, "Response gagal", Toast.LENGTH_SHORT).show()
        })
    }

    private fun setupListenerAllNews() {
        mainViewModels.requestAllNews(
            AppConstant.Q,
            AppConstant.API_KEY
        )
        mainViewModels.status.observe(this@MainActivity, Observer { status ->
            mainViewModels.status.value = null
            Toast.makeText(this@MainActivity, "Response gagal", Toast.LENGTH_SHORT).show()
        })
    }

    private fun observeHeadlineNews() {
        mainViewModels.headlineNewsObserver.observe(this@MainActivity) { response ->
            if(response.status == "ok") {
                binding.rvHeadline.apply {
                    adapter = headlineNewsAdapter
                    setHasFixedSize(true)
                    layoutManager = LinearLayoutManager(
                        this@MainActivity,
                        LinearLayoutManager.HORIZONTAL,
                        false
                    )
                    headlineNewsAdapter.setData(response.articles)
                }
            } else {
                Toast.makeText(this@MainActivity, "Data tidak ditemukan", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun observeAllNews() {
        mainViewModels.allNewsObserver.observe(this@MainActivity) { response ->
            if(response.status == "ok") {
                binding.rvAllNews.apply {
                    adapter = allNewsAdapter
                    setHasFixedSize(true)
                    layoutManager = LinearLayoutManager(
                        this@MainActivity,
                        LinearLayoutManager.VERTICAL,
                        false
                    )
                    allNewsAdapter.setData(response.articles)
                }
            } else {
                Toast.makeText(this@MainActivity, "Data tidak ditemukan", Toast.LENGTH_SHORT).show()
            }
        }
    }

}