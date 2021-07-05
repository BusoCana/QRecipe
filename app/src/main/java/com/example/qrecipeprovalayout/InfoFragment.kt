package com.example.qrecipeprovalayout

import android.net.Uri
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_info.*
import kotlinx.android.synthetic.main.fragment_ingredients.*
import kotlinx.android.synthetic.main.fragment_note.*
import kotlinx.android.synthetic.main.fragment_preparation.*

class InfoFragment : Fragment() {

    private val TAG = "InfoFragment"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        //get recipe info from bundle arguments
        val recipeName: String = arguments?.getString("recipe name").toString()
        val recipeInfo: List<String> = arguments?.getString("recipe info").toString().split("-")
        val recipePresentation: String = arguments?.getString("recipe presentation").toString().replace("\\n", "\n")
        val imageURL: String = arguments?.getString("image").toString()

        //set up image view
        Picasso.get().load(imageURL).into(recipeImageView)

        //set up text view
        recipeNameView.text = recipeName
        difficultyValueView.text = recipeInfo[0]
        preparationValueView.text = recipeInfo[1]
        cookingValueView.text = recipeInfo[2]
        dosesValueView.text = recipeInfo[3]
        costValueView.text = recipeInfo[4]
        caloriesValueView.text = recipeInfo[5]
        presentationView.text = recipePresentation

    }
}