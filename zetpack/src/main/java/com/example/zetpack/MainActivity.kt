package com.example.zetpack

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import android.widget.Toolbar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.zetpack.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private  lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)




        binding.btnNext.setOnClickListener{
            startActivity(Intent(this, SecondActivity::class.java))

        }
    }

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.main_menu, menu)
//
//        var searchItem = menu?.findItem(R.id.menuSearch)
//        var searchView = searchItem?.actionView as SearchView
//
//
//        searchView.setOnQueryTextListener(object:SearchView.OnQueryTextListener{
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                Toast.makeText(this@MainActivity, "검색:$query", Toast.LENGTH_SHORT).show()
//                return true
//            }
//
//            override fun onQueryTextChange(newText: String?): Boolean {
//                return true
//            }
//        })
//        return  true
//
//
//    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.menu1 ->{
                Toast.makeText(this, "메뉴 1선택", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.menu2 ->{
                Toast.makeText(this, "메뉴 2선택", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)

        }
    }

}