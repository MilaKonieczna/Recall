package com.example.recall.st

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.recall.R

class StartST : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_start_st, container, false)

        val start = view.findViewById<TextView>(R.id.start)

        start.setOnClickListener {
            val intent = Intent(activity, ST::class.java)
            startActivity(intent)
        }

        return view
    }
}
