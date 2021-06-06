package com.example.qrecipeprovalayout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_ingredients.*

class IngredientsFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ingredients, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //default recipe for test
        //val recipe: Recipe = Recipe("Carbonara", "Easy", 15, 10, 4, "Low", 680, "Carbonara Presentation...", ingr, "Carbonara Preparation...", "Carbonara Preservation...", "Carbonara Advice...")
        val ingr: MutableList<String> = mutableListOf("Spaghetti-320 g", "Tuorli-6", "Pepe nero-q.b.", "Guanciale-150 g", "Pecorino romano-50 g")

        //pass data to Adapter
        ingredientsListView.adapter = MyAdapter(this.requireContext(), ingr)

        orderButton.setOnClickListener {
            //TODO: address control implementation
            Toast.makeText(this.requireContext(), "Ordine Ingredienti...", Toast.LENGTH_SHORT).show()
        }
    }
}