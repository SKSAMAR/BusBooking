package com.samar.busbooking.data.remote.dto.ticket.checkticket


import com.google.gson.annotations.SerializedName

data class Passenger(
    @SerializedName("address")
    var address: String?,
    @SerializedName("age")
    var age: String?,
    @SerializedName("email")
    var email: String?,
    @SerializedName("gender")
    var gender: String?,
    @SerializedName("idNumber")
    var idNumber: String?,
    @SerializedName("idType")
    var idType: String?,
    @SerializedName("mobile")
    var mobile: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("primary")
    var primary: String?,
    @SerializedName("singleLadies")
    var singleLadies: String?,
    @SerializedName("title")
    var title: String?
):java.io.Serializable{
    override fun toString(): String {
        return "Address : $address\n\nAge : $age\n\nEmail : $email\n\nGender : $gender\n\nId Number : $idNumber\n\nID Type : $idType\n\nMobile : $mobile\n\nName : $name\n\nPrimary : $primary\n\nSingle Ladies : $singleLadies\n\nTitle : $title".replace("false", "No", true).replace("true", "Yes", true)
    }
}