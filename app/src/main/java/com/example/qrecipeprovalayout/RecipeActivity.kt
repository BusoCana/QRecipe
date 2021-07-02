package com.example.qrecipeprovalayout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_recipe.*
import kotlinx.android.synthetic.main.fragment_info.*

class RecipeActivity : AppCompatActivity() {

    private val TAG = "RecipeActivity"

    private val info_frag = InfoFragment()
    private val ingredients_frag = IngredientsFragment()
    private val preparation_frag = PreparationFragment()
    private val note_frag = NoteFragment()

    private lateinit var database: DatabaseReference
    private val recipe: MutableList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe)
        setSupportActionBar(toolbar)
        replaceFragments(info_frag)

        Log.v(TAG, "onCreate")

        //get recipe info from firebase
        getRecipeInfo(intent.getStringExtra("recipe name").toString())

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

        //bottom navigation bar listener
        bottom_nav.setOnNavigationItemSelectedListener(){
            when(it.itemId) {
                R.id.item_info -> replaceFragments(info_frag)
                R.id.item_ingredients -> replaceFragments(ingredients_frag)
                R.id.item_preparation -> replaceFragments(preparation_frag)
                R.id.item_note -> replaceFragments(note_frag)
            }
            true
        }

    }

    private fun getRecipeInfo(recipeName: String) {
        Log.v(TAG, "getRecipeInfo")

        //val temp: ArrayList<String> = ArrayList(7)

        //if recipe name has been read successfully enter into the if
        if(recipeName.isNotEmpty()) {
            database = FirebaseDatabase.getInstance("https://qrecipeprovalayout-2aed1-default-rtdb.firebaseio.com/").getReference("recipe")
            database.child(recipeName).get().addOnCompleteListener {

                //if it successfully read a data enter into the if
                if (it.isSuccessful) {

                    //if exist a recipe == recipe_name enter into the if
                    if(it.result?.exists() == true) {
                        //insert result value into data snapshot
                        val snapshot: DataSnapshot = it.result!!

                        //get recipe info from firebase and put it into local recipe
                        recipe.add(snapshot.child("name").value.toString())
                        recipe.add(snapshot.child("info").value.toString())
                        recipe.add(snapshot.child("presentation").value.toString())
                        recipe.add(snapshot.child("ingredients").value.toString())
                        recipe.add(snapshot.child("preparation").value.toString())
                        recipe.add(snapshot.child("preservation").value.toString())
                        recipe.add(snapshot.child("advice").value.toString())

                        //Log
                        Log.v(TAG, recipe[0])
                        Log.v(TAG, recipe[1])
                        Log.v(TAG, recipe[2])
                        Log.v(TAG, recipe[3])
                        Log.v(TAG, recipe[4])
                        Log.v(TAG, recipe[5])
                        Log.v(TAG, recipe[6])

                    } else {
                        Log.v(TAG, "Error - Recipe not Found")
                        Toast.makeText(this, "Error - Recipe not Found", Toast.LENGTH_LONG).show()
                    }

                } else {
                    Log.v(TAG, "Error - Failed to Read")
                    Toast.makeText(this, "Error - Failed to Read", Toast.LENGTH_LONG).show()
                }
            }

        } else {
            Log.v(TAG, "Error - Recipe Name not Received")
            Toast.makeText(this, "Error - Recipe Name not Received", Toast.LENGTH_LONG).show()
        }

    }

    //makes it appear toolbar_menu.xml items
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    //bottom navigation fragment replace
    private fun replaceFragments (fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainerView, fragment)
        transaction.commit()
    }

}
