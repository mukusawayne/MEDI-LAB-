package com.modcom.medilabsapp

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import java.text.SimpleDateFormat
import java.util.*

class SignUpActivity : AppCompatActivity() {
    private lateinit var buttonDatePicker: Button
    private lateinit var editTextDate: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        buttonDatePicker = findViewById(R.id.buttonDatePicker)
        editTextDate = findViewById(R.id.editTextDate)

        buttonDatePicker.setOnClickListener {
            showDatePickerDialog()
        }//end onclick
    }//end oncreate

    //other functions
    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()

        // Create a date picker dialog and set the current date as the default selection
        val datePickerDialog = DatePickerDialog(
            this,
            { _: DatePicker, year: Int, month: Int, day: Int ->
                val selectedDate = formatDate(year, month, day)
                editTextDate.setText(selectedDate)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        // Show the date picker dialog
        datePickerDialog.show()
    }

    private fun formatDate(year: Int, month: Int, day: Int): String {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, day)
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return dateFormat.format(calendar.time)
    }


}//end class