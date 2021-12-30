package com.udhipe.musemviewer.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.udhipe.musemviewer.R

class DetailActivity : AppCompatActivity() {

    companion object {
        const val OBJECT_NUMBER = "object_number"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
    }
}