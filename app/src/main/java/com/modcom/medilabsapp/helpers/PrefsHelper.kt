package com.modcom.medilabsapp.helpers

import android.content.Context
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor

//Shared preferences are used to values in a Key - value Approach
class PrefsHelper {
    companion object{
        fun savePrefs(context: Context, key: String, value:String){
            val pref: SharedPreferences = context.getSharedPreferences("store",
            Context.MODE_PRIVATE)
            val editor = pref.edit()
            editor.putString(key, value)
            editor.apply()
        }//end save

        fun getPrefs(context: Context, key: String) : String{
            val pref: SharedPreferences = context.getSharedPreferences("store",
                Context.MODE_PRIVATE)
            val value = pref.getString(key, "")//key is empty or not exist return empty
            return value.toString()
        }//end get

        fun clearPrefsByKey(context: Context, key: String){
            val pref: SharedPreferences = context.getSharedPreferences("store",
                Context.MODE_PRIVATE)
            val editor = pref.edit()
            editor.remove(key)
            editor.apply()
        }//end

        fun clearPrefs(context: Context){
            val pref: SharedPreferences = context.getSharedPreferences("store",
                Context.MODE_PRIVATE)
            val editor = pref.edit()
            editor.clear()
            editor.apply()
        }



    }//end companion
}//end class