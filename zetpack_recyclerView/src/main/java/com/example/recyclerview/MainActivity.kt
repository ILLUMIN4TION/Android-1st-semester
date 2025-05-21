package com.example.recyclerview

import android.os.Bundle
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.recyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private  lateinit var  binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        //어댑터에 데이터 전달

        val items = List(20){"Item${it+1}"} //1부터 20까지의 데이터를 list로 만듬

        val adapter = MyAdapter(items)
        binding.recyclerView.adapter = adapter
        //방금 만든 어댑터를 리사이클러뷰의 어댑터로 설정함

        // 아이템 데코레이션 추가,

        binding.recyclerView.addItemDecoration(MyItemDecoration(this))
    }
}