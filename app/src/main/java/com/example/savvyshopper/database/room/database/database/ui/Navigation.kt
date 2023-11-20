package com.example.savvyshopper.database.room.database.database.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.savvyshopper.database.room.database.database.ui.itemdetail.DetailScreen

enum class Routes{
    Home,
    Detail
}

@Composable
fun SavvyShopperNavigation(
    navHostController: NavHostController = rememberNavController()
) {

    NavHost(navController = navHostController, startDestination = Routes.Home.name){
        composable(route = Routes.Home.name){
            HomeScreen(onNavigate = { id ->
                navHostController.navigate(route = "${Routes.Detail.name}?id=$id")
            })
        }
        composable(
            route = "${Routes.Detail.name}?id={id}",
            arguments = listOf(navArgument("id"){type = NavType.IntType})
        ){
            val id = it.arguments?.getInt("id") ?: -1
            DetailScreen(id = id) {
                navHostController.navigateUp()
            }
        }
    }
}