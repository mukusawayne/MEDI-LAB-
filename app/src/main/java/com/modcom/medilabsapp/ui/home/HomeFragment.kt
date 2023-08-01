package com.modcom.medilabsapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textview.MaterialTextView
import com.modcom.medilabsapp.databinding.FragmentHomeBinding
import com.modcom.medilabsapp.helpers.PrefsHelper
import org.json.JSONObject

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        //Code here
        val userObject = PrefsHelper.getPrefs(requireContext(), "userObject")
        val user = JSONObject(userObject) //convert to JSON Object
        //Text View 1
        val surname = _binding!!.surname  //find view
        surname.text = "Surname: "+ user.getString("surname")
        //Text View 2
        val others = _binding!!.others  //find view
        others.text = "Others: "+user.getString("others")

       //gender, dob, reg_date, email



        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}