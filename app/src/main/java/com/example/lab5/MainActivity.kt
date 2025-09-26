package com.example.lab5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.lab5.ui.detail.PokemonDetailScreen
import com.example.lab5.ui.list.PokemonListScreen
import com.example.lab5.ui.theme.Lab5Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab5Theme {
                MaterialTheme {
                    val nav = rememberNavController()
                    NavHost(navController = nav, startDestination = "list") {
                        composable("list") {
                            PokemonListScreen(onPokemonClick = { name ->
                                nav.navigate("detail/$name")
                            })
                        }
                        composable(
                            route = "detail/{name}",
                            arguments = listOf(navArgument("name") { type = NavType.StringType })
                        ) { backStackEntry ->
                            val name = backStackEntry.arguments?.getString("name")!!
                            PokemonDetailScreen(name = name, onBack = { nav.popBackStack() })
                        }
                    }
                }
            }
        }
    }
}
