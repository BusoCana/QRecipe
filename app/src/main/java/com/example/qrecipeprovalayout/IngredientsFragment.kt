package com.example.qrecipeprovalayout

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_settings.*
import kotlinx.android.synthetic.main.fragment_ingredients.*

class IngredientsFragment : Fragment() {

    private val TAG = "IngredientsFragment"

    //things of preferences
    private lateinit var sharedPreferences: SharedPreferences
    private val myPreferences = "QRecipeProvaLayoutPreferences"
    private val Name = "nameKey"
    private val Surname = "surnameKey"
    private val Address = "addressKey"
    private val Cellular = "cellularKey"
    private val Email = "emailKey"

    //user info
    private lateinit var userName: String
    private lateinit var userSurname: String
    private lateinit var userAddress: String
    private lateinit var userCellular: String
    private lateinit var userEmail: String

    private lateinit var database: DatabaseReference

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
            //get user info from preferences
            sharedPreferences = requireActivity().getSharedPreferences(myPreferences, Context.MODE_PRIVATE)

            if (sharedPreferences.contains(Name))
                userName = sharedPreferences.getString(Name, "").toString()
            if (sharedPreferences.contains(Surname))
                userSurname = sharedPreferences.getString(Surname, "").toString()
            if (sharedPreferences.contains(Address))
                userAddress = sharedPreferences.getString(Address, "").toString()
            if (sharedPreferences.contains(Cellular))
                userCellular = sharedPreferences.getString(Cellular, "").toString()
            if (sharedPreferences.contains(Email))
                userEmail = sharedPreferences.getString(Email, "").toString()

            if(userName.isNotEmpty() && userSurname.isNotEmpty() && userAddress.isNotEmpty() && userCellular.isNotEmpty() && userEmail.isNotEmpty()) {
                val order = Order(arguments?.getString("recipe ingredients").toString(), userName, userSurname, userAddress, userCellular, userEmail)
                Log.v(TAG, order.toString())
                placeOrder(order)
            }
            else
                Toast.makeText(this.requireContext(), "Update Shipping Info in Settings", Toast.LENGTH_LONG).show()
        }
    }

    private fun placeOrder(order: Order) {
        database = FirebaseDatabase.getInstance("https://qrecipeprovalayout-2aed1-default-rtdb.firebaseio.com/").getReference("order")

        database.child(order.id).setValue(order).addOnSuccessListener {
            Log.v(TAG, "Order " + order.id + " placed")
            Toast.makeText(this.requireContext(), "Order " + order.id + " placed, wait notify on " + order.userCellular, Toast.LENGTH_LONG).show()
        }.addOnFailureListener {
            Log.v(TAG, "Order " + order.id + " failed to place")
            Toast.makeText(this.requireContext(), "Order " + order.id + " failed to place, please retry", Toast.LENGTH_LONG).show()
        }

    }
}