package com.udhipe.musemviewer.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import com.udhipe.musemviewer.R
import com.udhipe.musemviewer.databinding.ActivityMainBinding
import com.udhipe.musemviewer.ui.home.HomeFragment

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var drawerToggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        drawerToggle =
            ActionBarDrawerToggle(
                this@MainActivity,
                mainBinding.layoutDrawer, R.string.open, R.string.close
            )
        mainBinding.layoutDrawer.addDrawerListener(drawerToggle)
        drawerToggle.syncState()

        setListener()

        val homeFragment = HomeFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.layout_container, homeFragment).commit()
    }

    private fun setListener() {
        mainBinding.navigationView.setNavigationItemSelectedListener(this@MainActivity)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return drawerToggle.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.home -> {
                showInfo("Home")
                val homeFragment = HomeFragment()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.layout_container, homeFragment).commit()
            }

            R.id.profile -> {
                showInfo("Profile")
            }
        }

        if (mainBinding.layoutDrawer.isDrawerVisible(GravityCompat.START)) {
            mainBinding.layoutDrawer.closeDrawer(GravityCompat.START)
        } else {
            mainBinding.layoutDrawer.openDrawer(GravityCompat.START)
        }

        return true
    }

    private fun showInfo(info: String) {
        Toast.makeText(this@MainActivity, info, Toast.LENGTH_SHORT).show()
    }
}