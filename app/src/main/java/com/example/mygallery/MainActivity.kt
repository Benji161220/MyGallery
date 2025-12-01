package com.example.mygallery

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.mygallery.fragments.GalleryFragment
import com.example.mygallery.fragments.HomeFragment
import com.example.mygallery.fragments.SlideshowFragment
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        navView.setNavigationItemSelectedListener(this)

        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, 0, 0
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, HomeFragment()).commit()
            supportActionBar?.title = "Home"
            navView.setCheckedItem(R.id.nav_home)
        }

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START)
                } else {
                    isEnabled = false
                    onBackPressedDispatcher.onBackPressed()
                }
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var title: String
        val fragment: Fragment

        when (item.itemId) {
            R.id.nav_home -> {
                title = "Home"
                fragment = HomeFragment()
            }
            R.id.nav_gallery -> {
                title = "Gallery"
                fragment = GalleryFragment()
            }
            R.id.nav_slideshow -> {
                title = "Slideshow"
                fragment = SlideshowFragment()
            }
            else -> {
                title = "Home"
                fragment = HomeFragment()
            }
        }

        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit()
        supportActionBar?.title = title
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}
