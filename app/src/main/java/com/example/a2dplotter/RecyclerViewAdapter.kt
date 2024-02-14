package com.example.a2dplotter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter(private var coordinates: MutableList<Pair<Float, Float>>, private val recyclerView: RecyclerView) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        val viewHolder = ViewHolder(itemView)
        viewHolder.editTextX.text.clear()
        viewHolder.editTextY.text.clear()
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val coordinate = coordinates[position]
        holder.textViewNumber.text = (position + 1).toString()
        holder.editTextX.setText(coordinate.first.toString())
        holder.editTextY.setText(coordinate.second.toString())

        holder.buttonDelete.setOnClickListener {
            coordinates.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, coordinates.size)
        }
    }

    override fun getItemCount(): Int {
        return coordinates.size
    }

    fun addData() {
        coordinates.add(Pair(0.0f, 0.0f))
        notifyItemInserted(coordinates.size - 1)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewNumber: TextView = itemView.findViewById(R.id.textViewNumber)
        val editTextX: EditText = itemView.findViewById(R.id.editTextX)
        val editTextY: EditText = itemView.findViewById(R.id.editTextY)
        val buttonDelete: Button = itemView.findViewById(R.id.delete_button)
    }

    fun getCoordinates(): List<Pair<Float, Float>> {
        for (position in 0 until coordinates.size) {
            val viewHolder = recyclerView.findViewHolderForAdapterPosition(position)
            if (viewHolder is ViewHolder) {
                val xValue = viewHolder.editTextX.text.toString().toFloatOrNull() ?: 0.0f
                val yValue = viewHolder.editTextY.text.toString().toFloatOrNull() ?: 0.0f
                coordinates[position] = Pair(xValue, yValue)
            }
        }
        return coordinates
    }
}