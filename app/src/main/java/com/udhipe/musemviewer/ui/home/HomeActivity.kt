package com.udhipe.musemviewer.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.udhipe.musemviewer.R
import com.udhipe.musemviewer.ThisApplication
import com.udhipe.musemviewer.data.collection.CollectionRepository
import com.udhipe.musemviewer.databinding.ActivityHomeBinding
import com.udhipe.musemviewer.ui.detail.DetailActivity

class HomeActivity : AppCompatActivity() {

    private val viewModel: HomeViewModel by viewModels {
        HomeViewModelFactory(CollectionRepository.instance)
    }

    private lateinit var homeBinding: ActivityHomeBinding
    private lateinit var adapter: CollectionAdapter

    private val onItemCallBack = object : CollectionAdapter.OnItemClickCallback {
        override fun onItemClick(objectNumber: String) {
            val intent = Intent(this@HomeActivity, DetailActivity::class.java)
            intent.putExtra(DetailActivity.OBJECT_NUMBER, objectNumber)
            startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(homeBinding.root)

        setAdapter()

        viewModel.getCollectionList(1)
        viewModel.apply {
            viewState.observe(
                this@HomeActivity, Observer {
                    if (it != null) {
                        if (it.error == null) {
                            val data = it.data
                            if (data != null) {
                                adapter.updateData(data)
                            }
                        }
                    }
                }
            )
        }
    }

    private fun setAdapter() {
        val linearLayoutManager =
            LinearLayoutManager(this@HomeActivity, LinearLayoutManager.VERTICAL, false)
        adapter = CollectionAdapter(onItemCallBack)
        homeBinding.rvMuseumCollection.layoutManager = linearLayoutManager
        homeBinding.rvMuseumCollection.adapter = adapter
    }
}