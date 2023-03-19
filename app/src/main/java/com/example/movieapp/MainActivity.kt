package com.example.movieapp


import MyNavigation
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.example.movieapp.ui.theme.MovieAppTheme
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.movieapp.models.Movie
import com.example.movieapp.models.getMovies
import com.example.movieapp.ui.Screen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {


         MovieAppTheme {
             MainContent()

                // A surface container using the 'background' color from the theme
                Surface(

                    color = MaterialTheme.colors.background
                ) {







                    MyNavigation()
      /**       var list = listOf("Ajay","Vijay","Prakash")
                    MessageList()
                    var clickCount by remember { mutableStateOf(0)}
                    SharedPrefsToggle("wjjlkjkljoinuojijkljlkjlkjlijlkjkljkljlkjlkklmklmwe", TRUE) { clickCount += 2 }
                    ClickCounter(clicks = clickCount, onClick = {clickCount += 2})
                    NamePicker("fsef", list) { clickCount += 2 }
       **/ }
            }
        }
    }
}

@Composable
fun MainContent() {
    val bodyContent = remember { mutableStateOf("Select menu to change content") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "App Title")
                },
                actions = {
                    TopAppBarDropdownMenu(bodyContent)
                })
        }) {padding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)) {
            Text(bodyContent.value)
        }
    }
}

@Composable
fun TopAppBarDropdownMenu(bodyContent: MutableState<String>) {
    val expanded = remember { mutableStateOf(false) }

    Box(
        Modifier
            .wrapContentSize(Alignment.TopEnd)
    ) {
        IconButton(onClick = {
            expanded.value = true
            bodyContent.value =  "Menu Opening"
        }) {
            Icon(
                Icons.Filled.MoreVert,
                contentDescription = "More Menu"
            )
        }
    }

    DropdownMenu(
        expanded = expanded.value,
        onDismissRequest = { expanded.value = false },
    ) {
        DropdownMenuItem(onClick = {
            expanded.value = false
            bodyContent.value = "First Item Selected"
        }) {
            Text("Favorites")
        }
    }
}


@Composable
fun HomeScreen(navController: NavHostController){
    MessageList( navController)
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MovieRow(movie: Movie, navController: NavHostController) {
    var expandedState by remember { mutableStateOf(false)}
    val rotationState by animateFloatAsState(
        targetValue = if(expandedState) 180f else 0f)
    var text by remember {
        mutableStateOf("")
    }

    Card(modifier = Modifier
        .fillMaxWidth()
        .animateContentSize(
            animationSpec = tween(
                delayMillis = 300,
                easing = LinearOutSlowInEasing
            )
        )
        .padding(5.dp),
        shape = RoundedCornerShape(corner = CornerSize(15.dp)),
        elevation = 5.dp,
        onClick = {
            expandedState = !expandedState
        }
    ) {
        Column {
            Box(modifier = Modifier
                .height(150.dp)
                .fillMaxWidth(),
                contentAlignment = Alignment.TopStart
            ) {
                val painter = rememberAsyncImagePainter(
                    ImageRequest.Builder(LocalContext.current)
                        .data(data = movie.images[0])
                        .apply(block = fun ImageRequest.Builder.() {

                        }).build()
                )
                val painterState = painter.state
                Image(
                    painter = painter,
                    contentDescription = "Movie Poster",
                    contentScale = ContentScale.FillWidth
                )
//if (painterState is AsyncImagePainter.State.Loading){
  //  CircularProgressIndicator()
    //        }
                Box(modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                    contentAlignment = Alignment.TopEnd
                ){
                    Icon(
                        tint = MaterialTheme.colors.secondary,
                        imageVector = Icons.Default.FavoriteBorder,
                        contentDescription = "Add to favorites")
                }
            }

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    movie.title,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.h6)
                IconButton(
                    modifier = Modifier
                        .alpha(ContentAlpha.medium)
                        .rotate(rotationState),
                    onClick = {
                        expandedState = !expandedState
                    }) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowUp,
                        contentDescription = "Show details")
                }

            }
            if(expandedState){
                Text(
                    text = movie.plot,
                    fontSize = MaterialTheme.typography.subtitle1.fontSize,
                    fontWeight = FontWeight.Normal,
                    maxLines = 5
                )
            }
            Button(onClick = {
                navController.navigate(Screen.DetailScreen.withArgs("Thomas"))
            },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text(text = "To Dew")
            }
        }
    }
}


@Composable
fun MessageList(navController: NavHostController) {
    val list = listOf(
        "A", "B", "C", "D"
    )
    //getMovies()
    val bodyContent = remember { mutableStateOf("Select menu to change content") }


    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { TopAppBar(title = {Text("Top App Bar")} ,
            actions = {
                TopAppBarDropdownMenu(bodyContent)
            })},


        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = { FloatingActionButton(onClick = {}){
            Icon(imageVector = Icons.Default.Add, contentDescription = "fab icon")
        } },
        drawerContent = { Text(text = "Drawer Menu 1") },
        content = { padding ->

            print(getMovies())
            LazyColumn {
                (
                        Modifier
                            .padding(padding))
                items(getMovies()) { message ->
                    MovieRow(message, navController)
                }
            }
        }
    )
}


@Composable
fun ClickCounter(clicks: Int, onClick: () -> Unit) {
    Button(onClick = onClick) {
        Text("I've been clicked $clicks times")
    }
}

@Composable
fun SharedPrefsToggle(
    text: String,
    value: Boolean,
    onValueChanged: (Boolean) -> Unit
) {
    Row {
        Text(text)
        Checkbox(checked = value, onCheckedChange = onValueChanged)
    }
}


/**
 * Display a list of names the user can click with a header
 */
@Composable
fun NamePicker(
    header: String,
    names: List<String>,
    onNameClicked: (String) -> Unit
) {
    Column {
        // this will recompose when [header] changes, but not when [names] changes
        Text(header, style = MaterialTheme.typography.body1)
        Divider()

        // LazyColumn is the Compose version of a RecyclerView.
        // The lambda passed to items() is similar to a RecyclerView.ViewHolder.
        LazyColumn {
            items(names) { name ->
                // When an item's [name] updates, the adapter for that item
                // will recompose. This will not recompose when [header] changes
                NamePickerItem(name, onNameClicked)
            }
        }
    }
}

/**
 * Display a single name the user can click.
 */
@Composable
private fun NamePickerItem(name: String, onClicked: (String) -> Unit) {
    Text(name, Modifier.clickable(onClick = { onClicked(name) }))
}

@ExperimentalMultiplatform
@Preview
@Composable
fun ExpendableCardPreview() {
}


