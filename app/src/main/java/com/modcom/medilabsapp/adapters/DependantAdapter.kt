package com.modcom.medilabsapp.adapters

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textview.MaterialTextView
import com.modcom.medilabsapp.CheckoutStep2GPS
import com.modcom.medilabsapp.LabTestsActivity
import com.modcom.medilabsapp.R
import com.modcom.medilabsapp.SingleLabTest
import com.modcom.medilabsapp.helpers.PrefsHelper
import com.modcom.medilabsapp.models.Dependant
import com.modcom.medilabsapp.models.Lab
import com.modcom.medilabsapp.models.LabTests

class DependantAdapter(var context: Context):
    RecyclerView.Adapter<DependantAdapter.ViewHolder>() {


    //Create a List and connect it with our model
    var itemList : List<Dependant> = listOf() //Its empty

    //Create a Class here, will hold our views in single_lab xml
    inner class  ViewHolder(itemView: View):  RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DependantAdapter.ViewHolder {
        //access/inflate the single lab xml
        val view = LayoutInflater.from(parent.context).inflate(R.layout.single_dependant,
            parent, false)

        return ViewHolder(view) //pass the single lab to ViewHolder
    }

    override fun onBindViewHolder(holder: DependantAdapter.ViewHolder, position: Int) {
         //Find your 3 text views
        val dep_name = holder.itemView.findViewById<MaterialTextView>(R.id.dep_name)
        val dep_others = holder.itemView.findViewById<MaterialTextView>(R.id.dep_others)
        val dep_dob = holder.itemView.findViewById<MaterialTextView>(R.id.dep_dob)
        //Assume one Lab
         val item = itemList[position]
         dep_name.text = item.surname
         dep_others.text = item.others
         dep_dob.text = item.dob
         holder.itemView.setOnClickListener {
             ////Is confirmation dialog Needed?
             val builder = AlertDialog.Builder(context)
             builder.setTitle("Confirm")
             builder.setMessage("Are you sure to Pick ${item.surname}")
             builder.setPositiveButton("Yes") { dialog, which ->
                 PrefsHelper.savePrefs(context, "dependant_id", item.dependant_id)
                 val i = Intent(context, CheckoutStep2GPS::class.java)
                 i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                 context.startActivity(i)
                 dialog.dismiss()
             }//end Yes
             builder.setNegativeButton("No") {dialog, which ->
                dialog.dismiss()
             }//end No
             val alertDialog = builder.create()
             alertDialog.show()
         }//end Listner
    }//end bind

    override fun getItemCount(): Int {
        return itemList.size  //Count how may Items in the List
    }

    //This is for filtering data
    fun filterList(filterList: List<Dependant>){
        itemList = filterList
        notifyDataSetChanged()
    }


    //Earlier we mentioned item List is empty!
    //We will get data from our APi, then bring it to below function
    //The data you bring here must follow the Lab model
    fun setListItems(data: List<Dependant>){
        itemList = data //map/link the data to itemlist
        notifyDataSetChanged()
    //Tell this adapter class that now itemList is loaded with data
    }
    //justpaste.it/cgaym
}