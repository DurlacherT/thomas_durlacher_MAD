package com.example.movieapp.ui.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.movieapp.ui.screens.shared.SimpleAppBar
import com.example.movieapp.models.Movie
import com.example.movieapp.models.getMovies

@Composable
fun DetailScreen(navController: NavController, movieId: String?) {
    val movie = getMovieId(getMovies(), movieId)
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
        MessageListDetail(navController, movie)
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MovieRowDetail(movie: Movie) {
    var expandedState by remember { mutableStateOf(false) }
    val rotationState by animateFloatAsState(targetValue = if (expandedState) 180f else 0f)
    Card(
        modifier =
        Modifier.fillMaxWidth()
            .animateContentSize(
                animationSpec = tween(delayMillis = 300, easing = LinearOutSlowInEasing)
            )
            .padding(5.dp),
        shape = RoundedCornerShape(corner = CornerSize(15.dp)),
        elevation = 5.dp,
        onClick = { expandedState = !expandedState }
    ) {
        Column {
            Box(
                modifier = Modifier.height(150.dp).fillMaxWidth(),
            ) {
                val painter =
                    rememberAsyncImagePainter(
                        ImageRequest.Builder(LocalContext.current)
                            .data(data = movie.images[0])
                            .build(),
                        contentScale = ContentScale.FillWidth
                    )
                val painterState = painter.state
                Image(
                    painter = painter,
                    contentDescription = "Movie Poster",
                    contentScale = ContentScale.FillWidth
                )
                Box(
                    modifier = Modifier.fillMaxSize().padding(10.dp),
                    contentAlignment = Alignment.TopEnd
                ) {
                    Icon(
                        tint = MaterialTheme.colors.secondary,
                        imageVector = Icons.Default.FavoriteBorder,
                        contentDescription = "Add to favorites"
                    )
                }
                if (painterState is AsyncImagePainter.State.Loading) {
                    CircularProgressIndicator()
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth().padding(5.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    movie.title,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.h6
                )
                IconButton(
                    modifier = Modifier.alpha(ContentAlpha.medium).rotate(rotationState),
                    onClick = { expandedState = !expandedState }
                ) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowUp,
                        contentDescription = "Show details"
                    )
                }
            }
            if (expandedState) {

                Text(
                    text = "Director: " + movie.director,
                    fontSize = MaterialTheme.typography.subtitle1.fontSize,
                    fontWeight = FontWeight.Normal,
                    maxLines = 5
                )
                Text(
                    text = "Released: " + movie.year,
                    fontSize = MaterialTheme.typography.subtitle1.fontSize,
                    fontWeight = FontWeight.Normal,
                    maxLines = 5
                )
                Text(
                    text = "Genre: " + movie.genre,
                    fontSize = MaterialTheme.typography.subtitle1.fontSize,
                    fontWeight = FontWeight.Normal,
                    maxLines = 5
                )
                Text(
                    text = "Actors: " + movie.actors,
                    fontSize = MaterialTheme.typography.subtitle1.fontSize,
                    fontWeight = FontWeight.Normal,
                    maxLines = 5
                )
                Text(
                    text = "Rating: " + movie.rating,
                    fontSize = MaterialTheme.typography.subtitle1.fontSize,
                    fontWeight = FontWeight.Normal,
                    maxLines = 5
                )
                Divider(color = Color.Black, thickness = 1.dp)
                Text(
                    text = "Plot: " + movie.plot,
                    fontSize = MaterialTheme.typography.subtitle1.fontSize,
                    fontWeight = FontWeight.Normal,
                    maxLines = 5
                )
            }
        }
    }
}

@Composable
fun MessageListDetail(navController: NavController, movie: Movie) {
    Scaffold(
        topBar = { SimpleAppBar(movie, navController) },
        content = { padding -> DetailMovieImages(movie, padding) }
    )
}

@Composable
fun DetailMovieImages(movie: Movie, paddingValues: PaddingValues) {
    Column {
        MovieRowDetail(movie)
        LazyColumn {
            (Modifier.padding(paddingValues))
            items(getMovies()) { message -> MovieImages(message) }
        }
    }
}

@Composable
fun MovieImages(movie: Movie) {
    Card {
        Box(
            modifier = Modifier.height(150.dp).fillMaxWidth(),
        ) {
            val painter =
                rememberAsyncImagePainter(
                    ImageRequest.Builder(LocalContext.current).data(data = movie.images[0]).build(),
                    contentScale = ContentScale.FillWidth
                )
            painter.state
            Image(
                painter = painter,
                contentDescription = "Movie Poster",
                contentScale = ContentScale.FillWidth
            )
        }
    }
}

fun getMovieId(movie: List<Movie>, movieId: String?): Movie {
    for (i in movie.indices) {
        if (movie[i].id == movieId) {
            return movie[i]
        }
    }
    return movie[0]
}
