package com.modcom.medilabsapp

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textview.MaterialTextView

class CheckoutStep2GPS : AppCompatActivity() {
    private  lateinit var editLatitude: TextInputEditText
    private lateinit var editLongitude: TextInputEditText
    private lateinit var getlocation: MaterialButton
    private lateinit var progress: ProgressBar
    private lateinit var skip: MaterialTextView
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout_step2_gps)
        editLatitude = findViewById(R.id.editLatitude)
        editLongitude = findViewById(R.id.editLongitude)
        progress = findViewById(R.id.progress)
        getlocation = findViewById(R.id.getlocation)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        progress.visibility = View.GONE
        getlocation.setOnClickListener {
             //TODO
        }//end
    }//end onCreate

    //Function to check if user accepted permission or not
    //If user has not accepted permissions, Give them dialog to decide
    fun requestLocation(){
        if(ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED){
                 ActivityCompat.requestPermissions(
                     this,
                     arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION,
                         android.Manifest.permission.ACCESS_COARSE_LOCATION),
                     123)
              }//end if
              else {
                  getLocation() //get Lat and Lon
              }
    }//end function
    fun getLocation(){
        fusedLocationClient.lastLocation
            .addOnSuccessListener {
                location ->
                location?.let {
                    editLatitude.setText(it.latitude.toString())
                    editLongitude.setText(it.longitude.toString())
                    progress.visibility = View.GONE

                } ?: run {
                    Toast.makeText(applicationContext, "Location Not Found",
                        Toast.LENGTH_SHORT).show()
                } //end run
            }//end success
            .addOnFailureListener { e ->
                Toast.makeText(applicationContext, "Error $e", Toast.LENGTH_SHORT).show()
            }//end Failure
    }//end function

}//end class