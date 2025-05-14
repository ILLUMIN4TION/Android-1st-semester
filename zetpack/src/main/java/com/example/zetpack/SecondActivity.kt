package com.example.zetpack

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class SecondActivity:AppCompatActivity() {
    override fun onCreateDescription(): CharSequence? {
        return super.onCreateDescription()
        setContentView(R.layout.activity_second)
        val toolbar: Toolbar = findViewById(R.id.toolbar_second)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "상세 화면"
        // up 버튼
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


    }
    override fun onSupportNavigateUp(): Boolean{
        finish()
        return true
    }
}