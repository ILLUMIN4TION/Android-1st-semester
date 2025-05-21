package com.example.zetpack2

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        //androidx 사용, android.app은 구버전임
        //프래그먼트 매니저는 트랜잭션 관리,
        val fragmentManager: FragmentManager = supportFragmentManager

        //트랜잭션 생성,
        val transaction: FragmentTransaction = fragmentManager.beginTransaction()


        if(savedInstanceState == null){ //처음시작 or 앱 종료후 다시 시작할 때
            transaction.add(R.id.fragmentContainer, HomeFragment(), "HomeFragment")
            //메인액티비티에 우리가 지정했던, 프래그먼트가 들어갈 부분에, homeFragment를 출력하겠다, 태그는 homefragment다
            transaction.addToBackStack("home")
            transaction.commit()
            //지금까지 했던 작업을 실행함(프래그먼트 추가 백스택 추가)
        }

        findViewById<Button>(R.id.btnSwitch).setOnClickListener{
            val newTransaction = fragmentManager.beginTransaction()
            newTransaction.replace(R.id.fragmentContainer, DetailFragment(), "DetailFragment")
            newTransaction.addToBackStack("detail")
            newTransaction.commit()
        }
    }

    override fun onBackPressed() {
        if(supportFragmentManager.backStackEntryCount >1){
            supportFragmentManager.popBackStack()
        }else{
            super.onBackPressed()
        }

    }
}