package com.kamilmarnik.talkit

import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import android.support.v4.widget.DrawerLayout
import android.support.design.widget.NavigationView
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, EditMessDialog.EditMessDialogListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout:DrawerLayout = findViewById(R.id.drawer_layout)
        val navView:NavigationView = findViewById(R.id.nav_view)
        navView.setNavigationItemSelectedListener(this)

        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        goToFragment(UserFragment(), supportFragmentManager, this, R.id.drawer_user)
    }

    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.drawer_shoutbox -> {
                supportFragmentManager.beginTransaction().replace(R.id.fragment_container, ShoutboxFragment()).commit()
            }
            R.id.drawer_user -> {
                supportFragmentManager.beginTransaction().replace(R.id.fragment_container, UserFragment()).commit()
            }
            R.id.drawer_settings -> {
                supportFragmentManager.beginTransaction().replace(R.id.fragment_container, SettingsFragment()).commit()
            }
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun applyContent(newContent: String) {
        MessageAdapter.EditedMess.newContent = newContent
        MessageAdapter.EditedMess.request(this)
    }
}
