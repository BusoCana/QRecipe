package com.example.qrecipeprovalayout

import android.content.Intent
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_recipe.*
import kotlinx.android.synthetic.main.fragment_info.*

class RecipeActivity : AppCompatActivity() {

    val info_frag=InfoFragment()
    val ingredients_frag=IngredientsFragment()
    val preparation_frag= PreparationFragment()
    val note_frag= NoteFragment()

    var mUserReference: DatabaseReference?= FirebaseDatabase.getInstance("https://listviewapp-fcc4c-default-rtdb.firebaseio.com/").getReference("users")//intent.getStringExtra("qr_piatto").toString())
    private var mUsersChildListener: ChildEventListener=getUsersChildEventListener()

    //lateinit var mUserReference: DatabaseReference
    //lateinit private var mUsersChildListener: ChildEventListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe)
        setSupportActionBar(toolbar)
        replaceFragments(info_frag)


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

        bottom_nav.setOnNavigationItemSelectedListener(){
            when(it.itemId){
                R.id.item_info-> replaceFragments(info_frag)
                R.id.item_ingredients->replaceFragments(ingredients_frag)
                R.id.item_preparation->replaceFragments(preparation_frag)
                R.id.item_note->replaceFragments(note_frag)
            }
            true

        }

        //change image of recipeImageView
        //recipeImageView.setImageResource(R.drawable.cabbonava)
    }

    //makes it appear toolbar_menu.xml items
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }


    private fun replaceFragments (fragemnt: Fragment){
        if(fragemnt!=null){
            val transaction= supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragmentContainerView, fragemnt)
            transaction.commit()
        }
    }


    //firebase
    override fun onStart() {
        super.onStart()
        if (mUsersChildListener == null) {
            mUsersChildListener = getUsersChildEventListener()
        }
        mUserReference!!.addChildEventListener(mUsersChildListener)
        Toast.makeText(this@RecipeActivity, "START", Toast.LENGTH_SHORT).show()
    }

    private fun getUsersChildEventListener(): ChildEventListener {
        val childEventListener= object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                Log.d("TAG","ADDED" + snapshot.key!!)
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                Log.d("TAG","CHANGED" + snapshot.key!!)
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
                Log.d("TAG","REMOVED" + snapshot.key!!)
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
                Log.d("TAG","MOVED" + snapshot.key!!)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("TAG","ERROR")
            }

        }
        return childEventListener
    }


    override fun onStop() {
        super.onStop()
        if (mUsersChildListener != null) {
            mUserReference!!.removeEventListener(mUsersChildListener) }
    }


    fun updateTextView(){


    }


}
