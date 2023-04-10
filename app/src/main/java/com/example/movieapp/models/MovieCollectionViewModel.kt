package com.example.movieapp.models

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel

class MovieCollectionViewModel : ViewModel() {

    private val _movieList = getMovies().toMutableStateList()
    val movieList: List<Movie>
        get() = _movieList

    fun getFavorites(): List<Movie> {
        return _movieList.filter { it.isFavorite }
    }

    fun toggleFavoriteMovie(movie: Movie) {
        movie.isFavorite = movie.isFavorite != true
    }

    fun addMovies(
        id: String,
        title: String,
        year: String,
        genres: List<ListItemSelectable>,
        director: String,
        plot: String,
        rating: String
    ) {
        _movieList.add(
            Movie(
                id = id,
                title = title,
                year = year,
                genre = genres.joinToString(),
                director = director,
                actors = "Sam Worthington, Zoe Saldana, Sigourney Weaver, Stephen Lang",
                plot = plot,
                images =
                listOf(
"https://www.shutterstock.com/image-vector/ui-image-placeholder-wireframes-apps-260nw-1037719204.jpg",                ),
                rating = rating
            )
        )
    }

    var regUser: RegisterUser = RegisterUser()

    var id: MutableState<String> = mutableStateOf(regUser.id)
    var isidValid: MutableState<Boolean> = mutableStateOf(true)
    var userIdErrMsg: MutableState<String> = mutableStateOf("")

    var title: MutableState<String> = mutableStateOf(regUser.title)
    var istitleValid: MutableState<Boolean> = mutableStateOf(true)
    var titleErrMsg: MutableState<String> = mutableStateOf("")

    var year: MutableState<String> = mutableStateOf(regUser.year)
    var isyearValid: MutableState<Boolean> = mutableStateOf(true)
    var yearErrMsg: MutableState<String> = mutableStateOf("")

    // var genreItems: MutableState<List<ListItemSelectable>> = mutableStateOf(regUser.genreItems)
    var isgenreItemsValid: MutableState<Boolean> = mutableStateOf(true)
    var genreItemsErrMsg: MutableState<String> = mutableStateOf("")

    val genres = Genre.values().toList()

    var genreItems =
        mutableStateOf(
            genres.map { genre -> ListItemSelectable(title = genre.toString(), isSelected = false) }
        )

    var director: MutableState<String> = mutableStateOf(regUser.director)
    var isdirectorValid: MutableState<Boolean> = mutableStateOf(true)
    var directorErrMsg: MutableState<String> = mutableStateOf("")

    var actors: MutableState<String> = mutableStateOf(regUser.actors)
    var isactorValid: MutableState<Boolean> = mutableStateOf(true)
    var actorErrMsg: MutableState<String> = mutableStateOf("")

    var plot: MutableState<String> = mutableStateOf(regUser.plot)
    var isplotValid: MutableState<Boolean> = mutableStateOf(true)
    var plotErrMsg: MutableState<String> = mutableStateOf("")

    var rating: MutableState<Float> = mutableStateOf(regUser.rating)
    var isratingValid: MutableState<Boolean> = mutableStateOf(true)
    var ratingErrMsg: MutableState<String> = mutableStateOf("")

    var isEnabledRegisterButton: MutableState<Boolean> = mutableStateOf(false)

    init {}

    private fun validateAdd() {
        isEnabledRegisterButton.value =
            !istitleValid.value &&
                    !isyearValid.value &&
                    !isgenreItemsValid.value &&
                    !isdirectorValid.value &&
                    !isactorValid.value &&
                    !isplotValid.value &&
                    !isratingValid.value
    }

    fun validateidName() {
        if (id.value.length >= 10) {
            isidValid.value = true
            userIdErrMsg.value = "User Name should be less than 10 chars"
        } else {
            isidValid.value = false
            userIdErrMsg.value = ""
        }
        validateAdd()
    }

    fun validatetitle() {
        if (title.value.isEmpty()) {
            istitleValid.value = true
            titleErrMsg.value = "Title should not be empty"
        } else {
            istitleValid.value = false
            titleErrMsg.value = ""
        }
        validateAdd()
    }

    fun validateyear() {
        if (year.value.isEmpty()) {
            isyearValid.value = true
            yearErrMsg.value = "Year should not be empty"
        } else {
            isyearValid.value = false
            yearErrMsg.value = ""
        }
        validateAdd()
    }

    fun validategenreItems() {
        if (genreItems.value.isEmpty()) {
            isgenreItemsValid.value = true
            genreItemsErrMsg.value = "No selection made"
        } else {
            isgenreItemsValid.value = false
            genreItemsErrMsg.value = ""
        }
        validateAdd()
    }

    fun validatedirector() {
        if (director.value.isEmpty()) {
            isdirectorValid.value = true
            directorErrMsg.value = "Director should not be empty"
        } else {
            isdirectorValid.value = false
            directorErrMsg.value = ""
        }
        validateAdd()
    }

    fun validateactor() {
        if (actors.value.isEmpty()) {
            isactorValid.value = true
            actorErrMsg.value = "Actor should not be empty"
        } else {
            isactorValid.value = false
            actorErrMsg.value = ""
        }
        validateAdd()
    }

    fun validateplot() {
        if (plot.value is String) {
            isplotValid.value = false
            plotErrMsg.value = ""
        } else {
            isplotValid.value = true
            plotErrMsg.value = "Input is not a String"
        }
        validateAdd()
    }

    fun validaterating() {
        if (rating.value.isNaN()) {
            isratingValid.value = true
            ratingErrMsg.value = "Rating should not be empty"
        } else {
            isratingValid.value = false
            ratingErrMsg.value = ""
        }
        validateAdd()
    }

}
