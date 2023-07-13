package com.modcom.medilabsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textview.MaterialTextView
import com.modcom.medilabsapp.adapters.LabTestsCartAdapter
import com.modcom.medilabsapp.helpers.SQLiteCartHelper

class MyCart : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_cart)
        //put total cost in a textview
        val total = findViewById<MaterialTextView>(R.id.total)
        val helper = SQLiteCartHelper(applicationContext)
        total.text = "Total: "+helper.totalCost()

        //Find recycler
        val recycler = findViewById<RecyclerView>(R.id.recycler)
        recycler.layoutManager = LinearLayoutManager(applicationContext)
        recycler.setHasFixedSize(true)
        //Access adapter and provide it with from getAllItems
        if(helper.getNumItems() == 0){
            Toast.makeText(applicationContext, "Your Cart is Empty",
                Toast.LENGTH_SHORT).show()
        }
        else {
            val adapter = LabTestsCartAdapter(applicationContext)
            adapter.setListItems(helper.getAllItems())//pass data
            recycler.adapter = adapter //link adapter to recycler
        }
    }
}