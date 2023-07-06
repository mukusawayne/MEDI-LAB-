package com.modcom.medilabsapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textview.MaterialTextView
import com.modcom.medilabsapp.R
import com.modcom.medilabsapp.models.Lab
import com.modcom.medilabsapp.models.LabTests

class LabTestsAdapter(var context: Context):
    RecyclerView.Adapter<LabTestsAdapter.ViewHolder>() {


    //Create a List and connect it with our model
    var itemList : List<LabTests> = listOf() //Its empty

    //Create a Class here, will hold our views in single_lab xml
    inner class  ViewHolder(itemView: View):  RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LabTestsAdapter.ViewHolder {
        //access/inflate the single lab xml
        val view = LayoutInflater.from(parent.context).inflate(R.layout.single_labtests,
            parent, false)

        return ViewHolder(view) //pass the single lab to ViewHolder
    }

    override fun onBindViewHolder(holder: LabTestsAdapter.ViewHolder, position: Int) {
         //Find your 3 text views
        val test_name = holder.itemView.findViewById<MaterialTextView>(R.id.test_name)
        val test_description = holder.itemView.findViewById<MaterialTextView>(R.id.test_description)
        val test_cost = holder.itemView.findViewById<MaterialTextView>(R.id.test_cost)
        //Assume one Lab
         val item = itemList[position]
         test_name.text = item.test_name
         test_description.text = item.test_description
         test_cost.text = item.test_cost
    }

    override fun getItemCount(): Int {
        return itemList.size  //Count how may Items in the List
    }

    //This is for filtering data
    fun filterList(filterList: List<LabTests>){
        itemList = filterList
        notifyDataSetChanged()
    }


    //Earlier we mentioned item List is empty!
    //We will get data from our APi, then bring it to below function
    //The data you bring here must follow the Lab model
    fun setListItems(data: List<LabTests>){
        itemList = data //map/link the data to itemlist
        notifyDataSetChanged()
    //Tell this adapter class that now itemList is loaded with data
    }
    //justpaste.it/cgaym
}