package com.udhipe.musemviewer.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.core.view.GravityCompat
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.udhipe.musemviewer.R
import com.udhipe.musemviewer.data.collection.Collection
import com.udhipe.musemviewer.data.collection.CollectionRepository
import com.udhipe.musemviewer.databinding.ActivityDetailBinding
import com.udhipe.musemviewer.ui.home.HomeFragment

class DetailActivity : AppCompatActivity() {

    private val viewModel: DetailViewModel by viewModels {
        DetailViewModelFactory(CollectionRepository.instance)
    }

    private lateinit var detailBinding: ActivityDetailBinding

    companion object {
        const val OBJECT_NUMBER = "object_number"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(detailBinding.root)

        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val objectNumber = intent.getStringExtra(OBJECT_NUMBER)

        objectNumber?.let { viewModel.getSpecificCollection(it) }
        viewModel.apply {
            viewState.observe(this@DetailActivity, Observer {
                if (it != null) {
                    if (it.error == null) {
                        val data = it.data
                        if (data != null) {
                            showDetail(data)
                        }
                    }
                }
            })
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun showDetail(data: Collection) {
        with(detailBinding) {
            Glide.with(this@DetailActivity)
                .load(data.webImage.url)
                .centerCrop()
                .placeholder(R.color.magenta)
                .into(imgCollection)

            tvCollectionName.text = data.title
        }
    }
}