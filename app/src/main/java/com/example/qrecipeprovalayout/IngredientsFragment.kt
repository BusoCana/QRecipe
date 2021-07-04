package com.example.qrecipeprovalayout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_ingredients.*

class IngredientsFragment : Fragment() {

    private val TAG = "IngredientsFragment"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ingredients, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //get recipe info from bundle arguments
        val ingredients: List<String> = arguments?.getString("recipe ingredients").toString().split("-")

        //pass data to Adapter
        ingredientsListView.adapter = MyAdapter(this.requireContext(), ingredients)

        orderButton.setOnClickListener {
            //TODO: address control implementation
            Toast.makeText(this.requireContext(), "Order Placed", Toast.LENGTH_SHORT).show()
        }
    }
}