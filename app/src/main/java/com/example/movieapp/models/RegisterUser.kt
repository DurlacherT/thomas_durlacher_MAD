package com.example.movieapp.models

data class RegisterUser(
    var id: Int = 1,
    var title: String = "",
    var year: String = "",
    var genreItems: List<ListItemSelectable> = mutableListOf(),
    var director: String = "",
    var actors: String = "",
    var plot: String = "",
    var rating: Float = 0f

)