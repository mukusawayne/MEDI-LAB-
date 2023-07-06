package com.modcom.medilabsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.gson.GsonBuilder
import com.modcom.medilabsapp.adapters.LabAdapter
import com.modcom.medilabsapp.adapters.LabTestsAdapter
import com.modcom.medilabsapp.helpers.ApiHelper
import com.modcom.medilabsapp.models.Lab
import com.modcom.medilabsapp.models.LabTests
import org.json.JSONArray
import org.json.JSONObject

class LabTestsActivity : AppCompatActivity() {
    lateinit var itemList: List<LabTests>
    lateinit var labtestAdapter: LabTestsAdapter
    lateinit var recyclerView: RecyclerView
    lateinit var progress: ProgressBar
    lateinit var swiperefresh: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lab_tests)
        progress = findViewById(R.id.progress)
        recyclerView = findViewById(R.id.recycler)
        labtestAdapter = LabTestsAdapter(applicationContext)
        recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.setHasFixedSize(true)

        post_fetch()

    }//end oncreate

    fun post_fetch(){
        val api = "https://modcom.pythonanywhere.com/api/lab_tests"
        //Above APi needs a Body, So we have to build it
        val body = JSONObject()
        body.put("lab_id", 4) //NB: 4 is static
        val helper = ApiHelper(applicationContext)
        helper.post(api, body, object : ApiHelper.CallBack{
            override fun onSuccess(result: JSONArray?) {
                val gson = GsonBuilder().create()
                itemList = gson.fromJson(result.toString(),
                    Array<LabTests>::class.java).toList()
                //Finally, our adapter has the data
                labtestAdapter.setListItems(itemList)
                //For the sake of recycling/Looping items, add the adapter to recycler
                recyclerView.adapter = labtestAdapter
            }
            override fun onSuccess(result: JSONObject?) {
                Toast.makeText(applicationContext, result.toString(),
                    Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(result: String?) {

            }
        })
    }//end fetch

}