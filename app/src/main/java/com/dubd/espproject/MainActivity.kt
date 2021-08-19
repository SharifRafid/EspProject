package com.dubd.espproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(FirebaseAuth.getInstance().currentUser!=null){
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }
    }

    fun loginNow(view: View) {
        val email = email.text.toString()
        val passwordd = password.text.toString()
        if(email.isNotEmpty() && passwordd.isNotEmpty()){
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, passwordd).addOnCompleteListener {
                if(it.isSuccessful){
                    startActivity(Intent(this, HomeActivity::class.java))
                }else{
                    Toast.makeText(this, "Try Again", Toast.LENGTH_SHORT)
                }
            }
        }else{
            Toast.makeText(this, "Enter Email and Password", Toast.LENGTH_SHORT)
        }
    }
}