package com.example.downloadapp.features

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import com.example.downloadapp.R
import com.example.downloadapp.databinding.ActivityMainBinding
import com.example.downloadapp.features.main.MainPageFragment
import com.example.downloadapp.features.main.MainPageFragmentDirections
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {


    private lateinit var drawerLayout: DrawerLayout
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragmentContainerView) as NavHostFragment

        navController = navHostFragment.navController
        drawerLayout = binding.drawerLayout
        NavigationUI.setupActionBarWithNavController(this,navController)
        NavigationUI.setupWithNavController(binding.navigationView,navController)
        NavigationUI.setupActionBarWithNavController(this,navController,drawerLayout)
        binding.navigationView.setNavigationItemSelectedListener(this)

    }

    override fun onSupportNavigateUp(): Boolean {

        return NavigationUI.navigateUp(navController,drawerLayout)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.flashlights -> {
                navController.navigate(MainPageFragmentDirections.actionMainPageFragmentToApplicationFragment("flashlight"))
                drawerLayout.closeDrawer(GravityCompat.START)
            }
            R.id.coloredLights -> {
                navController.navigate(MainPageFragmentDirections.actionMainPageFragmentToApplicationFragment("coloredLight"))
                drawerLayout.closeDrawer(GravityCompat.START)

            }
            else -> {
                navController.navigate(MainPageFragmentDirections.actionMainPageFragmentToApplicationFragment("sosAlert"))
                drawerLayout.closeDrawer(GravityCompat.START)
            }
        }
        return NavigationUI.onNavDestinationSelected(item, navController)
    }

}