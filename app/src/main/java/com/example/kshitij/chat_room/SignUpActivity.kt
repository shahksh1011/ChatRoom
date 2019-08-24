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

    private lateinit var mAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        mAuth = FirebaseAuth.getInstance()

//        Toast.makeText(this, "TETX", Toast.LENGTH_LONG).show()

        signin_userSignIn.setOnClickListener {

            if (validate()){
//                Toast.makeText(this, "Ready to Sign In", Toast.LENGTH_LONG).show()
                mAuth.createUserWithEmailAndPassword(user_email.text.toString(), user_password.text.toString()).addOnCompleteListener {
                    if (it.isSuccessful){
                        val user = mAuth.currentUser
                        Toast.makeText(applicationContext, user?.email.toString(), Toast.LENGTH_LONG).show()
                    }
                    else{
                        Toast.makeText(applicationContext, "User Not Created", Toast.LENGTH_LONG).show()
                    }
                }
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
