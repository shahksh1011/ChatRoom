package com.example.kshitij.chat_room

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_first_page.*

class FirstPageActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mAuth = FirebaseAuth.getInstance()
        setContentView(R.layout.activity_first_page)
        signUpButton.setOnClickListener {
            val intent = Intent(applicationContext, SignUpActivity::class.java)
            startActivity(intent)
        }
        loginButton.setOnClickListener {
            mAuth.signInWithEmailAndPassword("kshah49@uncc.edu", "test1234").addOnCompleteListener {
                if (it.isSuccessful){
                    val user = mAuth.currentUser
                    Toast.makeText(this, user?.email.toString(),Toast.LENGTH_LONG).show()
                }
                else{
                    Log.d("Error", it.result.toString())
                    Toast.makeText(this, "REJECT",Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}
