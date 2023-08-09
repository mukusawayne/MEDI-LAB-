package com.modcom.medilabsapp

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textview.MaterialTextView
import com.modcom.medilabsapp.helpers.NetworkHelper

class Screen2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_screen2)
        if (NetworkHelper.checkForInternet(applicationContext)){
            Toast.makeText(applicationContext, "You are Connected",
                Toast.LENGTH_SHORT).show()
        }
        else {
            Toast.makeText(applicationContext, "You are Not Connected",
                Toast.LENGTH_SHORT).show()
            //Kill the app, Intent to
            val builder = AlertDialog.Builder(this@Screen2)
            builder.setTitle("Internet")
            builder.setMessage("You are Not Connected\n Please Connect to Internet")
            builder.setPositiveButton("OK") { dialog, which ->
                finishAffinity()
            }//end
            builder.setCancelable(false)
            builder.show()
        }//end

        val skip2 = findViewById<MaterialTextView>(R.id.skip2)
        skip2.setOnClickListener {
            startActivity(Intent(applicationContext, MainActivity::class.java))
        }//end

        val fab2 = findViewById<FloatingActionButton>(R.id.fab2)
        fab2.setOnClickListener {
            startActivity(Intent(applicationContext, MainActivity::class.java))
        }// End

     //Android OptionsMenu Kotlin

    }
}