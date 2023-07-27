package com.modcom.medilabsapp.ui.dependants

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.modcom.medilabsapp.MainActivity
import com.modcom.medilabsapp.R
import com.modcom.medilabsapp.helpers.PrefsHelper


class DependantFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_dependant, container, false)
        //Code here

        return root

    }

}