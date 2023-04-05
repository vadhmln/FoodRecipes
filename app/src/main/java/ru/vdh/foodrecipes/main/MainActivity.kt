package ru.vdh.foodrecipes.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import ru.vdh.foodrecipes.R
import ru.vdh.foodrecipes.databinding.ActivityMainBinding
import ru.vdh.foodrecipes.recipedetails.presentation.viewmodel.RecipeDetailsViewModel

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
//        setTheme(ru.vdh.cleanarch.core.ui.R.style.AppTheme)
//        splashScreen.setKeepOnScreenCondition { true }
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        navController = navHostFragment.navController
//        appBarConfiguration = AppBarConfiguration(navController.graph)

        val navView: BottomNavigationView = binding.bottomNavigationView
        appBarConfiguration = AppBarConfiguration(
            setOf(
                ru.vdh.foodrecipes.recipes.ui.R.id.nav_recipes,
                ru.vdh.foodrecipes.favoriterecipes.ui.R.id.nav_favorite_recipes,
                ru.vdh.foodrecipes.foodjoke.ui.R.id.nav_food_joke
            )
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        Log.e("AAA", "MainActivity created!!!")

    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}