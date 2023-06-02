package com.arqunn.usatoday.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.findNavController
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
        setContentView(binding.root)
        setupNavController()
    }

    private fun initListeners() = with(binding) {
        val noBottomNavigationIds = listOf(
            R.id.navigation_news_detail,
            R.id.navigation_search
        )
        navController.addOnDestinationChangedListener { _, destination, _ ->
            val shouldShowBottomNavigation = noBottomNavigationIds.contains(destination.id).not()
            bottomAppBar.isVisible = shouldShowBottomNavigation
            fab.isVisible = shouldShowBottomNavigation
        }

        fab.setOnClickListener {
            navController.navigate(R.id.navigation_search, null)
        }
    }

    private fun setupNavController() = with(binding) {
        navController = findNavController(R.id.nav_host_fragment)
        bottomNavigationView.background = null
        bottomNavigationView.setupWithNavController(navController)
    }

    fun showAddedToFavoritesDialog() {
        val dialog = AddedToFavoritesDialog(this)
        dialog.show()
    }
}