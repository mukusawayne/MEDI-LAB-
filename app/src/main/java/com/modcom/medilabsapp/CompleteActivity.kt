package com.modcom.medilabsapp
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import org.bouncycastle.jce.provider.BouncyCastleProvider
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec
import java.util.Base64

import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.google.android.material.textview.MaterialTextView
import com.modcom.medilabsapp.constants.Constants
import com.modcom.medilabsapp.helpers.ApiHelper
import com.modcom.medilabsapp.helpers.PrefsHelper
import com.modcom.medilabsapp.helpers.SQLiteCartHelper
import org.json.JSONArray
import org.json.JSONObject
import java.security.Security
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.CountDownLatch
import javax.crypto.spec.IvParameterSpec

class CompleteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_complete)
        // Get the required payload to make booking from the Shared preferences
        // Payload

            val test_id = 3
            val lab_id = 35

            val member_id = PrefsHelper.getPrefs(this, "member_id")
            val date = PrefsHelper.getPrefs(this, "date")
            val time = PrefsHelper.getPrefs(this, "time")
            val booked_for = PrefsHelper.getPrefs(this, "booked_for")
            val where_taken = PrefsHelper.getPrefs(this, "where_taken")
            val latitude = PrefsHelper.getPrefs(this, "latitude")
            val longitude = PrefsHelper.getPrefs(this, "longitude")
            val dependant_id = 2
            val invoice_no = "45454545"

            val helper = ApiHelper(this)
            val api = Constants.BASE_URL + "/make_booking"
            val body = JSONObject()
            body.put("member_id", member_id)
            body.put("appointment_date", date)
            body.put("appointment_time", time)
            body.put("booked_for", booked_for)
            body.put("where_taken", where_taken)
            body.put("latitude", latitude)
            body.put("longitude", longitude)
            body.put("dependant_id", dependant_id)
            body.put("test_id", test_id)
            body.put("lab_id", lab_id)
            body.put("invoice_no", invoice_no)

            helper.post(api, body, object : ApiHelper.CallBack {
                override fun onSuccess(result: JSONObject?) {
                    Toast.makeText(applicationContext, result.toString(), Toast.LENGTH_SHORT).show()

                }

                override fun onFailure(result: String?) {
                    Toast.makeText(applicationContext, result.toString(), Toast.LENGTH_SHORT).show()
                }

                override fun onSuccess(result: JSONArray?) {

                }
            })


    }//generate invoice

    fun generateInvoiceNumber(): String {
        val dateFormat = SimpleDateFormat("yyyyMMddHHmmss")
        val currentTime = Date()
        val timestamp = dateFormat.format(currentTime)

        // You can use a random number or a sequential number to add uniqueness to the invoice number
        // For example, using a random number:
        val random = Random()
        val randomNumber = random.nextInt(1000) // Change the upper bound as needed

        // Combine the timestamp and random number to create the invoice number
        return "INV-$timestamp-$randomNumber"
    }


}