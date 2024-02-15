package com.example.a2dplotter

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var addButton: Button
    private lateinit var chartButton: Button
    private lateinit var adapter: RecyclerViewAdapter
    private lateinit var graphView: CustomGraphView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.coordinates_list)
        graphView = findViewById(R.id.graph_view)
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
            graphView.setCoordinates(adapter.getCoordinates())
        }
    }
}