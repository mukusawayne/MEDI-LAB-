package com.modcom.medilabsapp.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textview.MaterialTextView
import com.modcom.medilabsapp.LabTestsActivity
import com.modcom.medilabsapp.R
import com.modcom.medilabsapp.models.Lab

class LabAdapter(var context: Context):
    RecyclerView.Adapter<LabAdapter.ViewHolder>() {


    //Create a List and connect it with our model
    var itemList : List<Lab> = listOf() //Its empty

    //Create a Class here, will hold our views in single_lab xml
    inner class  ViewHolder(itemView: View):  RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LabAdapter.ViewHolder {
        //access/inflate the single lab xml
        val view = LayoutInflater.from(parent.context).inflate(R.layout.single_lab,
            parent, false)

        return ViewHolder(view) //pass the single lab to ViewHolder
    }

    override fun onBindViewHolder(holder: LabAdapter.ViewHolder, position: Int) {
         //Find your 3 text views
        val lab_name = holder.itemView.findViewById<MaterialTextView>(R.id.lab_name)
        val permit_id = holder.itemView.findViewById<MaterialTextView>(R.id.permit_id)
        val email = holder.itemView.findViewById<MaterialTextView>(R.id.email)
        //Assume one Lab
         val lab = itemList[position]
         lab_name.text = lab.lab_name
         permit_id.text = lab.permit_id
         email.text = lab.email
        //When one Lab is clicked, Move to Lab tests Activity

         holder.itemView.setOnClickListener {
             val i = Intent(context, LabTestsActivity::class.java)
             i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
             context.startActivity(i)
         }

    }

    override fun getItemCount(): Int {
        return itemList.size  //Count how may Items in the List
    }

    //This is for filtering data
    fun filterList(filterList: List<Lab>){
        itemList = filterList
        notifyDataSetChanged()
    }


    //Earlier we mentioned item List is empty!
    //We will get data from our APi, then bring it to below function
    //The data you bring here must follow the Lab model
    fun setListItems(data: List<Lab>){
        itemList = data //map/link the data to itemlist
        notifyDataSetChanged()
    //Tell this adapter class that now itemList is loaded with data
    }
    //justpaste.it/cgaym
}