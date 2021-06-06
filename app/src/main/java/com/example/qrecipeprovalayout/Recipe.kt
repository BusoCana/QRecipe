package com.example.qrecipeprovalayout

class Recipe(var name: String, var difficulty: String, var preparation: Int, var cooking: Int, var doses: Int, var cost: String,
             var calories: Int, var presentation: String, var ingredients: List<String>, var preparationText: String, var preservation: String, var advice: String) {

    constructor(): this("", "", 0, 0, 0, "", 0, "", emptyList(), "", "", "")

    //TODO: check ingredientsToString work correctly
    private fun ingredientsToString(): String {
        var temp: String = ""

        ingredients.forEach {
            temp = temp + it + ","
        }

        return temp
    }

    override fun toString(): String {
        return name + "|" + difficulty + "|" + preparation + "|" + cooking + "|" + doses + "|" + cost + "|" + calories + "|" + presentation + "|" + ingredientsToString() + "|" + preparationText + "|" + preservation + "|" + advice
    }
}