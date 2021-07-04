package com.example.qrecipeprovalayout

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_preparation.*

class PreparationFragment : Fragment() {

    private val TAG = "PreparationFragment"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_preparation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recipePreparationView.movementMethod = ScrollingMovementMethod()

        //get recipe info from bundle arguments
        val preparation: String = arguments?.getString("recipe preparation").toString().replace("\\n", "\n")

        //set up text view
        recipePreparationView.text = preparation
    }
}