package com.example.zetpackcompliation

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.zetpackcompliation.databinding.ActivityMainBinding

/*
toolbar, drawer, viewpager, search 기능을 모두 구현

1. MyFragment 페이지 어댑터 클래스 뷰 페이저 구현 inner class 사용
2. onCreate 초기화,. actionbarIcon처리, toggle객체 처리,
3. toolbar 오른쪽 메뉴를 생성하는 함수 -> onCreateOption 오버라이드해서 재정의 -> 서치뷰 구현
4. option menu 클릭시 호출될 함수 -> onOptionsitemSeleted 오버라이드
 */




class MainActivity : AppCompatActivity() {

    lateinit var toggle: ActionBarDrawerToggle //토클이라는 변수를 사용해서 토글객체 만들기

    class MyFragmentPagerAdapter(activity: FragmentActivity):FragmentStateAdapter(activity){
        val fragments: List<Fragment>

        init{
            fragments = listOf(OneFragment(),TwoFragment(),ThreeFragment()) as List<Fragment>
        }
        //뷰 페이저 사용을 위한 반드시 구현해야하는 메서드들, ItemCount, createFragment
        override fun getItemCount(): Int {
            return fragments.size
        }

        override fun createFragment(position: Int): Fragment {
            return fragments[position]
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val binding  = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        toggle = ActionBarDrawerToggle(
            this, //지금 메인 액티비티에
            binding.main, //아까만든 main.xml을  drawble에 붙이기
            R.string.drawer_opened,
            R.string.drawer_closed
        )
        supportActionBar?.setDisplayHomeAsUpEnabled(true) //
        toggle.syncState() //우리가 만든 토글버튼과 실제로 연동

        val adapter = MyFragmentPagerAdapter(this) //뷰페이저를 어댑터 연결

        binding.viewPager.adapter = adapter
        //뷰페이저 코드 끝
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater

        inflater.inflate(R.menu.menu_main,menu) //우리가 만든 munu_main을 메뉴로 사용하겠음

        val menuItem = menu?.findItem(R.id.menu_search)
        val searchView = menuItem?.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                Log.d("test", "search Text: ${query}")
                return  true
            }

        })

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }




}