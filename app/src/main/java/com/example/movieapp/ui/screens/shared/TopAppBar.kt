package com.example.movieapp.ui.screens.shared

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.movieapp.models.Movie
import com.example.movieapp.ui.screens.Favorites

@Composable
fun SimpleAppBar(movie: Movie? = null, navController: NavController) {
    val bodyContent = remember { mutableStateOf("Select menu to change content") }
    val currentRoute = navController.currentBackStackEntry?.destination?.route
    TopAppBar(
        title = {
            if (movie != null) {
                Text(text = movie.title)
            }
            if (currentRoute == "home") {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Movies")
                    TopAppBarDropdownMenu(bodyContent, navController)
                }
            }
        },
        modifier = Modifier.fillMaxWidth(),
        navigationIcon =
        if (navController.previousBackStackEntry != null) {
            {
                Row() {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            }
        } else {
            null
        }
    )
}

@Composable
fun TopAppBarDropdownMenu(bodyContent: MutableState<String>, navController: NavController) {
    val expanded = remember { mutableStateOf(false) }
    Box(Modifier.wrapContentSize(Alignment.TopEnd)) {
        IconButton(
            onClick = {
                expanded.value = true
                bodyContent.value = "Menu Opening"
            }
        ) {
            Icon(Icons.Filled.MoreVert, contentDescription = "More Menu")
        }
    }
    DropdownMenu(
        expanded = expanded.value,
        onDismissRequest = { expanded.value = false },
    ) {
        DropdownMenuItem(
            onClick = {
                expanded.value = false
                bodyContent.value = "First Item Selected"
            }
        ) {
            IconButton(onClick = { navController.navigate("favorites") }, content = { Favorites() })
        }
    }
}
