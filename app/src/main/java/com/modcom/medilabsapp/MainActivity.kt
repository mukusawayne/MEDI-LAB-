package com.modcom.medilabsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
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
    lateinit var swiperefresh: SwipeRefreshLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        progress = findViewById(R.id.progress)
        recyclerView = findViewById(R.id.recycler)
        labAdapter = LabAdapter(applicationContext)
        recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.setHasFixedSize(true)
        //Call the function
       fetchData()
        swiperefresh = findViewById<SwipeRefreshLayout>(R.id.swipeRefreshLayout)
        swiperefresh.setOnRefreshListener {
            fetchData()// fetch data again
        }//end refresh

        //Filter labs
        val etsearch = findViewById<EditText>(R.id.etsearch)
        etsearch.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(texttyped: CharSequence?, p1: Int, p2: Int, p3: Int) {
                 filter(texttyped.toString())
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })




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
                swiperefresh.isRefreshing = false

            }//end

            override fun onSuccess(result: JSONObject?) {

            }

            override fun onFailure(result: String?) {
                progress.visibility = View.GONE
            }

        })
    }//end fetch data

    //Filter function
    //justpaste.it/9j21s
    //Filter
    private fun filter(text: String) {
        // creating a new array list to filter our data.
        val filteredlist: ArrayList<Lab> = ArrayList()

        // running a for loop to compare elements.
        for (item in itemList) {
            // checking if the entered string matched with any item of our recycler view.
            if (item.lab_name.lowercase().contains(text.lowercase())) {
                // if the item is matched we are
                // adding it to our filtered list.
                filteredlist.add(item)
            }
        }
        if (filteredlist.isEmpty()) {
            // if no item is added in filtered list we are
            // displaying a toast message as no data found.
            //Toast.makeText(this, "No Data Found..", Toast.LENGTH_SHORT).show()
            labAdapter.filterList(filteredlist)
        } else {
            // at last we are passing that filtered
            // list to our adapter class.
            labAdapter.filterList(filteredlist)
        }
    }


}