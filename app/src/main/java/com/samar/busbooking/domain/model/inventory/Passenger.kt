package com.samar.busbooking.domain.model.inventory

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class Passenger(
    val name: String = "",
    var mobile: String = "",
    val title: String = "",
    var email: String = "",
    val age: String = "",
    val gender: String = "",
    var address: String = "",
    var idNumber: String = "",
    var idType: String = "",
    var primary: String = ""
){
    override fun toString(): String {
        return "Passenger(name='$name', mobile='$mobile', title='$title', email='$email', age='$age', gender='$gender', address='$address', idNumber='$idNumber', primary='$primary')"
    }
}

fun Passenger.toPassengerMode(): PassengerMode{
    return PassengerMode(
        name = mutableStateOf(name), mobile= mutableStateOf(mobile),
        title = mutableStateOf(title), email = mutableStateOf(email),
        age = mutableStateOf(age), gender = mutableStateOf(gender),
        address = mutableStateOf(address), idNumber = mutableStateOf(idNumber),
        primary = mutableStateOf(primary)
    )
}

data class PassengerMode(
    val name: MutableState<String> = mutableStateOf(""),
    var mobile: MutableState<String> = mutableStateOf(""),
    val title: MutableState<String> = mutableStateOf(""),
    var email: MutableState<String> = mutableStateOf(""),
    val age: MutableState<String> = mutableStateOf(""),
    val gender: MutableState<String> = mutableStateOf(""),
    var address: MutableState<String> = mutableStateOf(""),
    var idNumber: MutableState<String> = mutableStateOf(""),
    var primary: MutableState<String> = mutableStateOf(""),
)

fun PassengerMode.toPassenger(): Passenger{
    return Passenger(name = name.value, mobile = mobile.value,
        title = title.value, email = email.value,
        age = age.value, gender = gender.value,
        address = address.value, idNumber = idNumber.value, primary = primary.value)
}
