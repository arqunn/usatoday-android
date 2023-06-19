package com.arqunn.usatoday.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.arqunn.usatoday.R
import com.arqunn.usatoday.databinding.ActivityMainBinding
import com.arqunn.usatoday.presentation.dialog.AddedToFavoritesDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        initListeners()
    }

    private fun init() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setSupportActionBar(binding.toolbar)
        setContentView(binding.root)
        setupNavController()
    }

    private fun initListeners() = with(binding) {
        val noBottomNavigationIds = listOf(R.id.navigation_news_detail)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            val shouldShowBottomNavigation = noBottomNavigationIds.contains(destination.id).not()
            navView.isVisible = shouldShowBottomNavigation
            toolbar.isVisible = shouldShowBottomNavigation
        }
    }

    private fun setupNavController() = with(binding) {
        navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_news,
                R.id.navigation_favorites,
                R.id.navigation_search
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    fun showAddedToFavoritesDialog() {
        val dialog = AddedToFavoritesDialog(this)
        dialog.show()
    }
}