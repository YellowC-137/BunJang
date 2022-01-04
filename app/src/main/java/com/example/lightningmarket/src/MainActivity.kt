package com.example.lightningmarket.src

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.lightningmarket.R
import com.example.lightningmarket.config.BaseActivity
import com.example.lightningmarket.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,R.id.navigation_search,R.id.navigation_post, R.id.navigation_talk, R.id.navigation_mypage
            )
        )


        //setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        setContentView(binding.root)
    }
}