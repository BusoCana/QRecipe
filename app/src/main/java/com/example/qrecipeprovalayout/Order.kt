package com.example.qrecipeprovalayout

import java.text.SimpleDateFormat
import java.util.*

class Order(var ingredients: String, var userName: String, var userSurname: String, var userAddress: String, var userCellular: String, var userEmail: String) {
    var id: String
    var placeDate: String

    init {
        this.id = userSurname + "-" + getCurrentDateTime().toString("ddMMyyyy-HHmmss")
        this.placeDate = getCurrentDateTime().toString("dd/MM/yyyy HH:mm:ss")
    }

    private fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
        val formatter = SimpleDateFormat(format, locale)
        return formatter.format(this)
    }

    private fun getCurrentDateTime(): Date {
        return Calendar.getInstance().time
    }

    override fun toString(): String {
        return "OrderID: " + id + "\nIngredients stamp: " + ingredients + "\nUser name: " + userName + "\nUser surname: " + userSurname +
                "\nUser address: " + userAddress + "\nUser cellular: " + userCellular + "\nUser email: " + userEmail + "\nPlace date: " + placeDate
    }
}