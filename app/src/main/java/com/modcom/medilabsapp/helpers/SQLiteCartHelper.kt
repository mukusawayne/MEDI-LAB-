package com.modcom.medilabsapp.helpers

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import java.util.zip.DeflaterOutputStream

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
  //Count How may items are there in the cart table
   fun getNumItems(): Int {
       val db = this.readableDatabase
        val result: Cursor = db.rawQuery("select * from cart", null)
        //return result count
        return result.count
   } //end

   //Clear all records
   fun clearCart(){
       val db = this.writableDatabase
       db.delete("cart", null, null)
       println("Cart Cleared")
       //TODO Toast , Refresh
   } //end

    //Remove One Item
   fun clearCartById(test_id: String){
       val db = this.writableDatabase
       //Provide the test_id when deleting
       db.delete("cart", "test_id=?", arrayOf(test_id))
       println("Item Id $test_id Removed")
        //TODO Toast , Refresh
   }//end


    fun totalCost(): Double {
        val db = this.readableDatabase
        val result: Cursor = db.rawQuery("select SUM(test_cost) from cart",
            null)
        var total: Double = 0.0
        while (result.moveToNext()){
            //the cursor result returns a Lists of test_cost.
            //Below result.getDouble(0) to retrieve the value from the first
            // column of the current row
            total += result.getDouble(0)
        }//end while
        return total
    }//End
    //https://github.com/modcomlearning/MediLabApp
    //Get all items from the Cart
    fun getAllItems(): String{
        val db = this.writableDatabase
        val result: Cursor = db.rawQuery("select * from cart", null)
        return result.toString() //?????
        //The result should follow LabTestModel
        //TODO Return the result that Matches the LabTestModel
    }









}