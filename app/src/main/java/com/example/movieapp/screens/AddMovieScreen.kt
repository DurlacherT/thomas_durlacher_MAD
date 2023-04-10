package com.example.movieapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.movieapp.R
import com.example.movieapp.models.MovieCollectionViewModel
import com.example.movieapp.widgets.SimpleTopAppBar

@Composable
fun AddMovieScreen(navController: NavController, viewModel: MovieCollectionViewModel) {
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            SimpleTopAppBar(arrowBackClicked = { navController.popBackStack() }) {
                Text(text = stringResource(id = R.string.add_movie))
            }
        },
    ) { padding ->
        MainContent(Modifier.padding(padding), viewModel)
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainContent(modifier: Modifier = Modifier, vm: MovieCollectionViewModel) {
    Surface(modifier = modifier.fillMaxWidth().fillMaxHeight().padding(10.dp)) {
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState()).fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            var title by remember { mutableStateOf("") }

            var year by remember { mutableStateOf("") }

            var director by remember { mutableStateOf("") }

            var actors by remember { mutableStateOf("") }

            var plot by remember { mutableStateOf("") }

            var rating by remember { mutableStateOf("") }

            var isEnabledSaveButton by remember { mutableStateOf(false) }

            Column() {
                OutlinedTextField(
                    value = vm.title.value,
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    onValueChange = {
                        vm.title.value = it
                        vm.validatetitle()
                    },
                    label = { Text(text = stringResource(R.string.enter_movie_title)) },
                    isError = vm.istitleValid.value
                )
                Text(
                    modifier = Modifier.padding(start = 8.dp),
                    text = vm.titleErrMsg.value,
                    fontSize = 14.sp,
                    color = Color.Red
                )
            }

            Column() {
                OutlinedTextField(
                    value = vm.year.value,
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    onValueChange = {
                        vm.year.value = it
                        vm.validateyear()
                    },
                    label = { Text(stringResource(R.string.enter_movie_year)) },
                    isError = vm.isyearValid.value
                )
                Text(
                    modifier = Modifier.padding(start = 8.dp),
                    text = vm.yearErrMsg.value,
                    fontSize = 14.sp,
                    color = Color.Red
                )
            }

            Text(
                modifier = Modifier.padding(top = 4.dp),
                text = stringResource(R.string.select_genres),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.h6
            )

            Column() {
                LazyHorizontalGrid(modifier = Modifier.height(100.dp), rows = GridCells.Fixed(3)) {
                    items(vm.genreItems.value) { genreItem ->
                        Chip(
                            modifier = Modifier.padding(2.dp),
                            colors =
                            ChipDefaults.chipColors(
                                backgroundColor =
                                if (genreItem.isSelected)
                                    colorResource(id = R.color.purple_200)
                                else colorResource(id = R.color.white)
                            ),
                            onClick = {
                                vm.genreItems.value =
                                    vm.genreItems.value.map {
                                        if (it.title == genreItem.title) {
                                            genreItem.copy(isSelected = !genreItem.isSelected)
                                        } else {
                                            it
                                        }
                                    }
                                vm.validategenreItems()
                            }
                        ) {
                            Text(text = genreItem.title)
                        }
                    }
                }
                Text(
                    modifier = Modifier.padding(start = 8.dp),
                    text = vm.genreItemsErrMsg.value,
                    fontSize = 14.sp,
                    color = Color.Red
                )
            }

            Column() {
                OutlinedTextField(
                    value = vm.director.value,
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    onValueChange = {
                        vm.director.value = it
                        vm.validatedirector()
                    },
                    label = { Text(stringResource(R.string.enter_director)) },
                    isError = vm.isdirectorValid.value
                )
                Text(
                    modifier = Modifier.padding(start = 8.dp),
                    text = vm.directorErrMsg.value,
                    fontSize = 14.sp,
                    color = Color.Red
                )
            }

            Column() {
                OutlinedTextField(
                    value = vm.actors.value,
                    modifier = Modifier.fillMaxWidth(),
                    onValueChange = {
                        vm.actors.value = it
                        vm.validateactor()
                    },
                    label = { Text(stringResource(R.string.enter_actors)) },
                    isError = vm.isactorValid.value
                )
                Text(
                    modifier = Modifier.padding(start = 8.dp),
                    text = vm.actorErrMsg.value,
                    fontSize = 14.sp,
                    color = Color.Red
                )
            }

            Column() {
                OutlinedTextField(
                    value = vm.plot.value,
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth().height(120.dp),
                    onValueChange = {
                        vm.plot.value = it
                        vm.validateplot()
                    },
                    label = {
                        Text(
                            textAlign = TextAlign.Start,
                            text = stringResource(R.string.enter_plot)
                        )
                    },
                    isError = vm.isplotValid.value
                )
                Text(
                    modifier = Modifier.padding(start = 8.dp),
                    text = vm.plotErrMsg.value,
                    fontSize = 14.sp,
                    color = Color.Red
                )
            }

            Column() {
                OutlinedTextField(
                    value = vm.rating.value.toString(),
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    onValueChange = {
                        vm.rating.value = it.toFloat()
                        vm.validaterating()
                        rating =
                            if (it.startsWith("0")) {
                                ""
                            } else {
                                it
                            }
                    },
                    label = { Text(stringResource(R.string.enter_rating)) },
                    isError = vm.isratingValid.value
                )
                Text(
                    modifier = Modifier.padding(start = 8.dp),
                    text = vm.ratingErrMsg.value,
                    fontSize = 14.sp,
                    color = Color.Red
                )
            }

            Button(
                enabled = vm.isEnabledRegisterButton.value,
                onClick = {
                    vm.addMovies(
                        "id",
                        vm.title.value,
                        vm.year.value,
                        vm.genreItems.value,
                        vm.director.value,
                        vm.plot.value,
                        vm.rating.toString()
                    )
                    vm.movieList.forEach { println(it) }
                    println("test")
                }
            ) {
                Text(text = stringResource(R.string.add))
            }
        }
    }
}
