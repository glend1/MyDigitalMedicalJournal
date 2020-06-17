package com.mydigitalmedicaljournal

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.mydigitalmedicaljournal.db.DataSource

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    //TODO mTablet isn't used?
    private var mTablet = false
    private lateinit var navController: NavController
    private lateinit var dataSource: DataSource

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        databaseConnect()
    }

    override fun onStart() {
        super.onStart()
        val drawerLayout: DrawerLayout? = findViewById(R.id.drawer_layout)
        isTablet(drawerLayout)
        setupNav(drawerLayout)
    }

    private fun databaseConnect() {
        dataSource = DataSource(this)
        dataSource.open()
    }

    private fun isTablet(drawerLayout: DrawerLayout?) {
        if (drawerLayout == null) {
            mTablet = true
        }
    }

    private fun setupNav(drawerLayout: DrawerLayout?) {
        val navView: NavigationView = findViewById(R.id.nav_view)
        navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val navSet = setOf(
            R.id.nav_journal, R.id.nav_reports, R.id.nav_calendar,
            R.id.nav_templates, R.id.nav_copyright, R.id.nav_instructions,
            R.id.nav_settings, R.id.nav_social, R.id.nav_support
        )
        appBarConfiguration = AppBarConfiguration(navSet, drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_settings -> {
                navController.navigate(R.id.nav_settings)
            }
            R.id.action_support -> {
                navController.navigate(R.id.nav_support)
            }
            R.id.action_social -> {
                navController.navigate(R.id.nav_social)
            }
            R.id.action_instructions -> {
                navController.navigate(R.id.nav_instructions)
            }
            R.id.action_copyright -> {
                navController.navigate(R.id.nav_copyright)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onDestroy() {
        dataSource.close()
        super.onDestroy()
    }
}
