package cz.janchvala.android.kotlin.ui.main

import android.content.res.Configuration
import android.os.Bundle
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import butterknife.bindView
import cz.janchvala.android.kotlin.R
import cz.janchvala.android.kotlin.ui.AbstractActivity
import cz.janchvala.android.kotlin.ui.util.ext.setContentFragment

public class MainActivity : AbstractActivity() {

    val toolBar: Toolbar by bindView(R.id.toolBar)
    val drawerLayout: DrawerLayout by bindView(R.id.drawerLayout)
    val drawerToggle: ActionBarDrawerToggle by lazy {
        ActionBarDrawerToggle(this, drawerLayout,
                R.string.main_activity_toolbar, R.string.main_activity_toolbar)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        setContentFragment(R.id.containerLayout, MainFragment.newInstance())
        setSupportActionBar(toolBar)
        supportActionBar.setDisplayHomeAsUpEnabled(true)
        drawerToggle.isDrawerIndicatorEnabled = true
        MainComponent.Initializer.init(this).inject(this)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        drawerToggle.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        drawerToggle.onConfigurationChanged(newConfig)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean =
            if (drawerToggle.onOptionsItemSelected(item)) true
            else super.onOptionsItemSelected(item)

}
