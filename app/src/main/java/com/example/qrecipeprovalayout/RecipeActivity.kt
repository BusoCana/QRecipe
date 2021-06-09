package com.example.qrecipeprovalayout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import kotlinx.android.synthetic.main.activity_recipe.*

class RecipeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe)
        setSupportActionBar(toolbar)

        //toolbar listener
        toolbar.setNavigationOnClickListener {
            //handle navigation icon press
            this.finish()
        }

        toolbar.setOnMenuItemClickListener { menuItem ->
            when(menuItem.itemId) {
                R.id.settings -> {
                    //handle settings icon press
                    val intent = Intent(this, SettingsActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }

        //change image of recipeImageView
        recipeImageView.setImageResource(R.drawable.cabbonava)
    }

    //makes it appear toolbar_menu.xml items
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    //button handler
    fun infoButtonOnClick(v: View) {
        //handle info button pressed
        infoButton.setBackgroundResource(R.drawable.left_button_selected)
        ingredientsButton.setBackgroundColor(resources.getColor(R.color.design_default_color_secondary))
        preparationButton.setBackgroundColor(resources.getColor(R.color.design_default_color_secondary))
        noteButton.setBackgroundResource(R.drawable.right_button)

        //open InfoFragment
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace<InfoFragment>(R.id.fragmentContainerView)
        }
    }

    fun ingredientsButtonOnClick(v: View) {
        //handle ingredients button pressed
        infoButton.setBackgroundResource(R.drawable.left_button)
        ingredientsButton.setBackgroundColor(resources.getColor(R.color.design_default_color_secondary_variant))
        preparationButton.setBackgroundColor(resources.getColor(R.color.design_default_color_secondary))
        noteButton.setBackgroundResource(R.drawable.right_button)

        //open IngredientsFragment
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace<IngredientsFragment>(R.id.fragmentContainerView)
        }
    }

    fun preparationButtonOnClick(v: View) {
        //handle preparation button pressed
        infoButton.setBackgroundResource(R.drawable.left_button)
        ingredientsButton.setBackgroundColor(resources.getColor(R.color.design_default_color_secondary))
        preparationButton.setBackgroundColor(resources.getColor(R.color.design_default_color_secondary_variant))
        noteButton.setBackgroundResource(R.drawable.right_button)

        //open PreparationFragment
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace<PreparationFragment>(R.id.fragmentContainerView)
        }
    }

    fun noteButtonOnClick(v: View) {
        //handle note button pressed
        infoButton.setBackgroundResource(R.drawable.left_button)
        ingredientsButton.setBackgroundColor(resources.getColor(R.color.design_default_color_secondary))
        preparationButton.setBackgroundColor(resources.getColor(R.color.design_default_color_secondary))
        noteButton.setBackgroundResource(R.drawable.right_button_selected)

        //open NoteFragments
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace<NoteFragment>(R.id.fragmentContainerView)
        }
    }
}
