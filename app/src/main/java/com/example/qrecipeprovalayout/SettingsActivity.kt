package com.example.qrecipeprovalayout

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_recipe.toolbar
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity() {
    val avatar = arrayOf(
        R.drawable.avatar_blue_cola,
        R.drawable.avatar_blue_pizza,
        R.drawable.avatar_green_apple,
        R.drawable.avatar_green_wine,
        R.drawable.avatar_red_drink,
        R.drawable.avatar_red_icecream,
        R.drawable.avatar_yellow_beer,
        R.drawable.avatar_yellow_sweet
    )
    var pos = 0

    //things of preferences
    lateinit var sharedPreferences: SharedPreferences
    val myPreferences = "QRecipeProvaLayoutPreferences"
    val Name = "nameKey"
    val Surname = "surnameKey"
    val Address = "addressKey"
    val Cellular = "cellularKey"
    val Email = "emailKey"
    val Pos = "posKey"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        setSupportActionBar(toolbar)

        //toolbar listener
        toolbar.setNavigationOnClickListener {
            //handle navigation icon press
            this.finish()
        }

        //get preferences info and set up the ImageView, EditText
        sharedPreferences = getSharedPreferences(myPreferences, Context.MODE_PRIVATE)

        if (sharedPreferences.contains(Name))
            nameView.setText(sharedPreferences.getString(Name, ""))
        if (sharedPreferences.contains(Surname))
            surnameView.setText(sharedPreferences.getString(Surname, ""))
        if (sharedPreferences.contains(Address))
            addressView.setText(sharedPreferences.getString(Address, ""))
        if (sharedPreferences.contains(Cellular))
            cellularView.setText(sharedPreferences.getString(Cellular, ""))
        if (sharedPreferences.contains(Email))
            emailView.setText(sharedPreferences.getString(Email, ""))
        if (sharedPreferences.contains(Pos)) {
            pos = sharedPreferences.getInt(Pos, 0)
            if(pos == 0)
                pos = 7
            else
                pos--
            avatarImageView.setImageResource(avatar[pos])
        }
    }

    fun changeAvatar(v: View) {
        //change avatar with the next
        avatarImageView.setImageResource(avatar[pos])
        if(pos+1 == 8)
            pos = 0
        else
            pos++
    }

    fun saveChanges(v: View) {
        //save user changes in preferences
        val editor = sharedPreferences.edit()

        editor.putString(Name, nameView.text.toString())
        editor.putString(Surname, surnameView.text.toString())
        editor.putString(Address, addressView.text.toString())
        editor.putString(Cellular, cellularView.text.toString())
        editor.putString(Email, emailView.text.toString())
        editor.putInt(Pos, pos)

        editor.commit()

        Toast.makeText(this, "Changes saved", Toast.LENGTH_SHORT).show()
    }
}