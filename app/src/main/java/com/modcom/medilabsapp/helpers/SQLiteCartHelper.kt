package com.modcom.medilabsapp.helpers

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class SQLiteCartHelper(context: Context):
    SQLiteOpenHelper(context, "cart.db", null, 1) {
    //SQLite helps store data Locally on your phone - You can CRUD
    override fun onCreate(sql: SQLiteDatabase?) {
           sql?.execSQL("CREATE TABLE IF NOT EXISTS cart(test_id Integer primary key, test_name varchar, test_cost Integer, lab_id Integer, test_description text)")
    }

    override fun onUpgrade(sql: SQLiteDatabase?, p1: Int, p2: Int) {
        sql?.execSQL("DROP TABLE IF EXISTS cart")
    }

    //INSERT Save to cart
    fun insert(test_id: String, test_name: String, test_cost: String,
    test_description: String, lab_id: String){
         val db = this.writableDatabase
         val values = ContentValues()
         values.put("test_id", test_id)
         values.put("test_name", test_name)
         values.put("test_cost", test_cost)
         values.put("test_description", test_description)
         values.put("lab_id", lab_id)
         //save
         val result:Long = db.insert("cart", null, values)
         if (result < 1){
             println("Failed to Add")
         }
        else {
            println("Item Added To Cart")
         }
    }//end
    //TODO GetRecords, Delete all, delete one, Gee Totals







}