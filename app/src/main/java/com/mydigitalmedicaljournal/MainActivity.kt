package com.mydigitalmedicaljournal

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.mydigitalmedicaljournal.db.DataSource

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController
    private lateinit var dataSource: DataSource
    private var drawerLayout: DrawerLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        setupNav()
        databaseConnect()
    }

    private fun databaseConnect() {
        dataSource = DataSource(this)
        dataSource.open()
    }

    private fun setupNav() {
        val navSet = setOf(R.id.nav_journal)
        drawerLayout = findViewById(R.id.drawer_layout)
        appBarConfiguration = AppBarConfiguration(navSet, drawerLayout)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        setupActionBarWithNavController(navController, appBarConfiguration)

        val navView: NavigationView = findViewById(R.id.nav_view)
        navView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, _, _ ->
                supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                drawerLayout?.open()
            }
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
