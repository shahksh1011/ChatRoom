package com.example.kshitij.chat_room

import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import android.support.v4.widget.DrawerLayout
import android.support.design.widget.NavigationView
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.widget.TextView
import android.widget.Toast
import com.example.kshitij.chat_room.Data.User
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val ACTIVITY_REQUEST = 1
//  PROJECT ID: chatroom-c6286
    private lateinit var mAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mAuth = FirebaseAuth.getInstance()
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)
    }

    override fun onStart() {
        super.onStart()
        Log.d("PROJECT", FirebaseApp.getInstance().options.projectId)
        val currUser = mAuth.currentUser
        Log.d("Current User", currUser.toString())
        if(currUser==null){
            val intent = Intent(applicationContext, LoginActivity::class.java)
            startActivityForResult(intent, ACTIVITY_REQUEST)

        }
        else{
            val user = User(currUser.displayName.toString(), currUser.email.toString())
            updateUI(user)

        }
//        updateUI(currUser)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ACTIVITY_REQUEST){
            val user = data?.getBundleExtra("User")
            Log.d("User--", user.toString())
        }
    }

    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_home -> {
                Toast.makeText(baseContext, "Home", Toast.LENGTH_LONG).show()
            }
            R.id.nav_chatrooms -> {
                Toast.makeText(baseContext, "Chatrooms", Toast.LENGTH_LONG).show()
            }
            R.id.nav_notifications -> {
                Toast.makeText(baseContext, "Notifications", Toast.LENGTH_LONG).show()
            }
            R.id.nav_logout->{
                Toast.makeText(baseContext, "Logout", Toast.LENGTH_LONG).show()
                mAuth.signOut()
                val intent = Intent(applicationContext, LoginActivity::class.java)
                startActivityForResult(intent, ACTIVITY_REQUEST)
//                finish()
            }
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    fun updateUI(user:User){
        Log.d("USER", user.name.toString() + " -- " + user.email.toString())
        val navView: NavigationView = findViewById(R.id.nav_view)
        val headerView = navView.getHeaderView(0)
        var user_email = headerView.findViewById<TextView>(R.id.user_email)
        var user_name = headerView.findViewById<TextView>(R.id.user_name)

        user_email.setText(user.email.toString())
        user_name.setText(user.name.toString())

    }
}
