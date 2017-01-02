package com.vsouhrada.android.kotlin.anko.drawer

import android.support.constraint.ConstraintLayout
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.support.v4.view.GravityCompat
import android.view.Gravity
import android.view.View
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.toolbar
import org.jetbrains.anko.design.appBarLayout
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.design.floatingActionButton
import org.jetbrains.anko.design.navigationView
import org.jetbrains.anko.support.v4._DrawerLayout
import org.jetbrains.anko.support.v4.drawerLayout

/**
 * @author vsouhrada
 * @see[AnkoComponent]
 * @see[MainActivity]
 * @since 0.1.0
 */
class DrawerView : AnkoComponent<MainActivity> {

  override fun createView(ui: AnkoContext<MainActivity>): View = with(ui) {
    drawerLayout {
      id = R.id.drawer_layout
      fitsSystemWindows = true
      lparams(width = matchParent, height = matchParent)
      createAppBar(ui)
      createNavigationView(ui)
    }

  }

  fun _DrawerLayout.createAppBar(ui: AnkoContext<MainActivity>) {
    coordinatorLayout {
      fitsSystemWindows = false
      lparams(width = matchParent, height = matchParent)

      appBarLayout(R.style.AppTheme_AppBarOverlay) {
        lparams(width = matchParent, height = wrapContent)

        toolbar {
          id = R.id.toolbar
          popupTheme = R.style.AppTheme_PopupOverlay
          backgroundResource = R.color.colorPrimary
          lparams(width = matchParent, height = ui.ctx.attrAsDimen(R.attr.actionBarSize))
        }
      }

      // https://github.com/Kotlin/anko/issues/210
      include<ConstraintLayout>(R.layout.content_main)

      floatingActionButton {
        id = R.id.fab
        imageResource = android.R.drawable.ic_dialog_email
        backgroundColor = ContextCompat.getColor(ui.owner, R.color.colorAccent)
        lparams {
          margin = resources.getDimensionPixelSize(R.dimen.fab_margin)
          gravity = Gravity.BOTTOM or GravityCompat.END
        }
        onClick {
          snackbar("Replace with your own action", Snackbar.LENGTH_LONG) {
            setAction("Action") { ui.toast("Clicked Snack") }
          }
        }
      }
    }
  }

  fun _DrawerLayout.createNavigationView(ui: AnkoContext<MainActivity>) {
    navigationView {
      id = R.id.nav_view
      fitsSystemWindows = true
      lparams(height = matchParent, gravity = GravityCompat.START)
      setNavigationItemSelectedListener(ui.owner)
      inflateHeaderView(R.layout.nav_header_main)
      inflateMenu(R.menu.activity_main_drawer)
    }
  }

}