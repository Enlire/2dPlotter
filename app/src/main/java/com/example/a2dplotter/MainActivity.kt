package com.example.a2dplotter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var addButton: Button
    private lateinit var chartButton: Button
    private lateinit var adapter: RecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.coordinates_list)
        addButton = findViewById(R.id.add_button)
        chartButton = findViewById(R.id.chart_button)
        adapter = RecyclerViewAdapter(mutableListOf(Pair(0.0f, 0.0f)), recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        addButton.setOnClickListener {
            adapter.addData()
        }

        chartButton.setOnClickListener {
            Log.i("coordinates", adapter.getCoordinates().toString())
        }
    }
}