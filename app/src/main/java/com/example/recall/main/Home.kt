package com.example.recall.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.recall.R
import com.example.recall.bnt.StartBNT
import com.example.recall.databinding.ActivityHomeBinding
import com.example.recall.dst.StartDST
import com.example.recall.results.PastResults
import com.example.recall.sst.StartSST
import com.example.recall.st.StartST

class Home : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragmentToLoad = intent.getStringExtra("fragmentToLoad")
        if (fragmentToLoad != null) {
            when (fragmentToLoad) {
                "StartSST" -> replaceFragment(StartSST())
            }
        } else {
            replaceFragment(StartSST())
        }

        binding.menu.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_bnt -> replaceFragment(StartBNT())
                R.id.nav_dst -> replaceFragment(StartDST())
                R.id.nav_sst -> replaceFragment(StartSST())
                R.id.nav_st -> replaceFragment(StartST())
                R.id.nav_res -> replaceFragment(PastResults())
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame, fragment)
            .commit()
    }
}
