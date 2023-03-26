import androidx.compose.runtime.*
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movieapp.ui.screens.DetailScreen
import com.example.movieapp.ui.screens.FavoriteScreen
import com.example.movieapp.ui.screens.HomeScreen

sealed class Screens(val route: String) {
    object Home : Screens("home")
    object Favorites : Screens("favorites")
    object Details : Screens("detail/{movieId}")
}

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.Home.route) {
        composable(route = Screens.Home.route) { HomeScreen(navController) }
        composable(route = Screens.Favorites.route) { FavoriteScreen(navController) }
        composable(
            route = Screens.Details.route,
            arguments = listOf(navArgument("movieId") { type = NavType.StringType })
        ) { backStackEntry ->
            DetailScreen(navController, movieId = backStackEntry.arguments?.getString("movieId"))
        }
    }
}
