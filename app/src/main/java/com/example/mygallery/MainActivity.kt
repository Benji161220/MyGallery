package com.example.mygallery

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.mygallery.fragments.MessageFragment
import com.google.android.material.navigation.NavigationView
import com.example.mygallery.R

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
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, MessageFragment.newInstance("Inbox")).commit()
            navView.setCheckedItem(R.id.nav_inbox)
            supportActionBar?.title = "Inbox"
        }

        // Setup Spinner in header
        val headerView = navView.getHeaderView(0)
        val spinner: Spinner = headerView.findViewById(R.id.email_spinner)
        val emails = arrayOf("carladom@gmail.com", "alu123@ieselcaminas.org", "correocarla@gmail.es")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, emails)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var title = ""
        when (item.itemId) {
            R.id.nav_inbox -> {
                title = "Inbox"
            }
            R.id.nav_outbox -> {
                title = "Outbox"
            }
            R.id.nav_trash -> {
                title = "Trash"
            }
            R.id.nav_spam -> {
                title = "Spam"
            }
        }
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, MessageFragment.newInstance(title)).commit()
        supportActionBar?.title = title
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}
