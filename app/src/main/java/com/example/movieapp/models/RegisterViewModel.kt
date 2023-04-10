package com.example.movieapp.models

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class RegisterViewModel : ViewModel() {

    var regUser: RegisterUser = RegisterUser()

    var id: MutableState<String> = mutableStateOf(regUser.id)
    var isidValid: MutableState<Boolean> = mutableStateOf(false)
    var userIdErrMsg: MutableState<String> = mutableStateOf("")

    var title: MutableState<String> = mutableStateOf(regUser.title)
    var istitleValid: MutableState<Boolean> = mutableStateOf(false)
    var titleErrMsg: MutableState<String> = mutableStateOf("")

    var year: MutableState<String> = mutableStateOf(regUser.year)
    var isyearValid: MutableState<Boolean> = mutableStateOf(false)
    var yearErrMsg: MutableState<String> = mutableStateOf("")

    var genreItems: MutableState<List<ListItemSelectable>> = mutableStateOf(regUser.genreItems)
    var isgenreItemsValid: MutableState<Boolean> = mutableStateOf(false)
    var genreItemsErrMsg: MutableState<List<ListItemSelectable>> = mutableStateOf(mutableListOf())

    var director: MutableState<String> = mutableStateOf(regUser.director)
    var isdirectorValid: MutableState<Boolean> = mutableStateOf(false)
    var directorErrMsg: MutableState<String> = mutableStateOf("")

    var actors: MutableState<String> = mutableStateOf(regUser.actors)
    var isactorValid: MutableState<Boolean> = mutableStateOf(false)
    var actorErrMsg: MutableState<String> = mutableStateOf("")

    var plot: MutableState<String> = mutableStateOf(regUser.plot)
    var isplotValid: MutableState<Boolean> = mutableStateOf(false)
    var plotErrMsg: MutableState<String> = mutableStateOf("")

    var rating: MutableState<Float> = mutableStateOf(regUser.rating)
    var isratingValid: MutableState<Boolean> = mutableStateOf(false)
    var ratingErrMsg: MutableState<Float> = mutableStateOf(0f)

    var isEnabledRegisterButton: MutableState<Boolean> = mutableStateOf(false)

    init { }

    private fun shouldEnabledRegisterButton() {
        isEnabledRegisterButton.value = userIdErrMsg.value.isEmpty()
                && titleErrMsg.value.isEmpty()
                && yearErrMsg.value.isEmpty()
                && genreItemsErrMsg.value.isEmpty()
                && directorErrMsg.value.isNotEmpty()
                && plotErrMsg.value.isNotEmpty()
    }

    fun validateidName() {
        if (id.value.length >= 10) {
            isidValid.value = true
            userIdErrMsg.value = "User Name should be less than 10 chars"
        } else {
            isidValid.value = false
            userIdErrMsg.value = ""
        }
        shouldEnabledRegisterButton()
    }

    fun validatetitle() {
        if (title.value.length >= 10) {
            istitleValid.value = true
            titleErrMsg.value = "User Name should be less than 10 chars"
        } else {
            istitleValid.value = false
            titleErrMsg.value = ""
        }
        shouldEnabledRegisterButton()
    }

    fun validateyear() {
        if (year.value != "123") {
            isyearValid.value = true
            yearErrMsg.value = "Password should be 123"
        } else {
            isyearValid.value = false
            yearErrMsg.value = ""
        }
        shouldEnabledRegisterButton()
    }

    fun validategenreItems() {
     /*   if (genreItems.value) {
            isgenreItemsValid.value = true
            genreItemsErrMsg.value = mutableListOf()
        } else {
            isgenreItemsValid.value = false
            genreItemsErrMsg.value = mutableListOf()
        }
        shouldEnabledRegisterButton()*/
    }

    fun validatedirector() {
        if (director.value != "123") {
            isdirectorValid.value = true
            directorErrMsg.value = "Password did not match"
        } else {
            isdirectorValid.value = false
            directorErrMsg.value = ""
        }
        shouldEnabledRegisterButton()
    }

    fun validateactor() {
        if (actors.value != "123") {
            isactorValid.value = true
            actorErrMsg.value = "Password did not match"
        } else {
            isactorValid.value = false
            actorErrMsg.value = ""
        }
        shouldEnabledRegisterButton()
    }


    fun validateplot() {
        if (plot.value != "123") {
            isplotValid.value = true
            plotErrMsg.value = "Password did not match"
        } else {
            isplotValid.value = false
            plotErrMsg.value = ""
        }
        shouldEnabledRegisterButton()
    }


    fun validaterating() {
        if (rating.value != 2f) {
            isratingValid.value = true
            ratingErrMsg.value = 2f
        } else {
            isratingValid.value = false
            ratingErrMsg.value = 2f
        }
        shouldEnabledRegisterButton()
    }
    fun register() {
        regUser.id = id.value
        regUser.title = title.value
        regUser.year = year.value
        regUser.genreItems = genreItems.value
        regUser.director = director.value
        regUser.plot = plot.value
        regUser.rating = rating.value

        Log.d("Karthik username", id.value)
        Log.d("Karthik email", title.value)
        Log.d("Karthik password", year.value)
        Log.d("Karthik confirmPassword", director.value)
        Log.d("Karthik", regUser.toString())
    }

}