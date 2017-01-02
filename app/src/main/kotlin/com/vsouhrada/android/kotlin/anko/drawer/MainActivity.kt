package com.vsouhrada.android.kotlin.anko.drawer

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import org.jetbrains.anko.find
import org.jetbrains.anko.setContentView
import org.jetbrains.anko.toast

/**
 * @author vsouhrada
 * @see[AppCompatActivity]
 * @since 0.1.0
 */
class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

  lateinit var drawer: DrawerLayout

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    DrawerView().setContentView(this)

    drawer = find<DrawerLayout>(R.id.drawer_layout)

    val toolbar = find<Toolbar>(R.id.toolbar)
    setSupportActionBar(toolbar)

    val toggle = ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)

    drawer.setDrawerListener(toggle)
    toggle.syncState()

    val navigationView = find<NavigationView>(R.id.nav_view)
    navigationView.setNavigationItemSelectedListener(this)
  }

  override fun onBackPressed() {
    if (drawer.isDrawerOpen(GravityCompat.START)) {
      drawer.closeDrawer(GravityCompat.START)
    } else {
      super.onBackPressed()
    }
  }

  override fun onCreateOptionsMenu(menu: Menu): Boolean {
    // Inflate the menu; this adds items to the action bar if it is present.
    menuInflater.inflate(R.menu.main, menu)
    return true
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    return when (item.itemId) {
      R.id.action_settings -> true
      else -> super.onOptionsItemSelected(item)
    }
  }

  override fun onNavigationItemSelected(item: MenuItem): Boolean {
    // Handle navigation view item clicks here.
    when (item.itemId) {
      R.id.nav_camera -> toast("Click Camera")
      R.id.nav_gallery -> toast("Click Gallery")
      R.id.nav_slideshow -> toast("Click Slideshow")
      R.id.nav_manage -> toast("Click Manage")
      R.id.nav_share -> toast("Click Share")
      R.id.nav_send -> toast("Click Send")
    }

    drawer.closeDrawer(GravityCompat.START)

    return true
  }

}