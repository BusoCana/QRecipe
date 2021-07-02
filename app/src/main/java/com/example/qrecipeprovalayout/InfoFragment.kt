package com.example.qrecipeprovalayout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_info.*
import kotlinx.android.synthetic.main.fragment_ingredients.*
import kotlinx.android.synthetic.main.fragment_note.*
import kotlinx.android.synthetic.main.fragment_preparation.*

class InfoFragment : Fragment() {

    //private val ra: RecipeActivity = RecipeActivity()
    //private lateinit var info: List<String>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*
        info = ra.getRecipeInfo("info").split("-")

        //set text view content
        recipeNameView.text = ra.getRecipeInfo("name")
        difficultyValueView.text = info[0]
        preparationValueView.text = info[1]
        cookingValueView.text = info[2]
        dosesValueView.text = info[3]
        costValueView.text = info[4]
        caloriesValueView.text = info[5]
         */

    }
}