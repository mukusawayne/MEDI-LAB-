package com.modcom.medilabsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.GsonBuilder
import com.modcom.medilabsapp.adapters.LabAdapter
import com.modcom.medilabsapp.helpers.ApiHelper
import com.modcom.medilabsapp.models.Lab
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    //Global Declaration - they can be accessed all over this class
     lateinit var itemList: List<Lab>
     lateinit var labAdapter: LabAdapter
     lateinit var recyclerView: RecyclerView
    lateinit var progress: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progress = findViewById(R.id.progress)
        recyclerView = findViewById(R.id.recycler)
        labAdapter = LabAdapter(applicationContext)
        recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.setHasFixedSize(true)


    }//end Oncreate

    fun fetchData(){
        //Go to the PAi get the data
        val api = "https://modcom.pythonanywhere.com/api/laboratories"
        val helper = ApiHelper(applicationContext)
        helper.get(api, object: ApiHelper.CallBack{
            override fun onSuccess(result: JSONArray?) {
                //Take above result to adapter
                //Convert Above result from JSON array to LIST<Lab>
                val gson = GsonBuilder().create()
                itemList = gson.fromJson(result.toString(),
                    Array<Lab>::class.java).toList()
                //Finally, our adapter has the data
                labAdapter.setListItems(itemList)
                //For the sake of recycling/Looping items, add the adapter to recycler
                recyclerView.adapter = labAdapter
                progress.visibility = View.GONE
            }//end

            override fun onSuccess(result: JSONObject?) {

            }

            override fun onFailure(result: String?) {
                progress.visibility = View.GONE
            }

        })
    }//end fetch data
}