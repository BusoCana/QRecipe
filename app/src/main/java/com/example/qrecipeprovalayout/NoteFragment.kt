package com.example.qrecipeprovalayout

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_note.*
import kotlinx.android.synthetic.main.fragment_preparation.*

class NoteFragment : Fragment() {

    private val TAG = "NoteFragment"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_note, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recipePreservationView.movementMethod = ScrollingMovementMethod()
        recipeAdviceView.movementMethod = ScrollingMovementMethod()

        //get recipe info from bundle arguments
        val preservation: String = arguments?.getString("recipe preservation").toString().replace("\\n", "\n")
        val advice: String = arguments?.getString("recipe advice").toString().replace("\\n", "\n")

        //set up text view
        recipePreservationView.text = preservation
        recipeAdviceView.text = advice
    }
}