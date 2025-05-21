package com.example.zetpack2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.zetpack2.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        //액티비티랑 다르게 container, false 인자가 나옴,

        // Inflate the layout for this fragment
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //추가적인 초기화 작업, 데이터 불러오기, 클릭 이벤트 처리를 위한-> 이벤트 리스너 등록 등
        super.onViewCreated(view, savedInstanceState)
    }


}