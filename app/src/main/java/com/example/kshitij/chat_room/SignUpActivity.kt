package com.example.kshitij.chat_room

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_first_page.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.activity_sign_up.view.*

class SignUpActivity : AppCompatActivity() {

    private var mAuth: FirebaseAuth? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        signin_userSignIn.setOnClickListener {
            if (validate()){
                Toast.makeText(applicationContext, "Ready to Sign In", Toast.LENGTH_LONG)
            }
        }
    }
    fun validate() :Boolean{
        var valid = true
        if(user_name.text.isEmpty()){
            user_name.error = "Invalid Name"
            valid = false
        }
        if (user_email.toString().isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(user_email.text).matches()){
            user_email.error = "Invalid Email Address"
            valid = false
        }
        if (user_password.toString().isEmpty() || user_password.length()<4 || user_password.length()>10){
            user_password.error = "Password Length should be between 4 and 10"
            valid = false
        }


        return valid

    }
}
