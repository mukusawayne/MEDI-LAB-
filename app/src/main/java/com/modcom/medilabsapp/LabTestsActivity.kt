package com.modcom.medilabsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.modcom.medilabsapp.adapters.LabAdapter
import com.modcom.medilabsapp.adapters.LabTestsAdapter
import com.modcom.medilabsapp.models.LabTests

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



    }
}