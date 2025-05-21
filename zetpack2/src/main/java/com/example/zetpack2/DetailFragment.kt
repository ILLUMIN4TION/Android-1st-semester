package com.example.zetpack2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.zetpack2.databinding.FragmentDetailBinding



class DetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //액티비티랑 다르게 container, false 인자가 나옴,

        // Inflate the layout for this fragment
        return  inflater.inflate(R.layout.fragment_detail,container,false)

    }


}