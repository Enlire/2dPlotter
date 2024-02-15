package com.example.a2dplotter
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class CustomGraphView(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    val paint = Paint().apply {
        color = Color.BLACK
        strokeWidth = 5f
        isAntiAlias = true
    }

    val gridPaint = Paint().apply {
        color = Color.GRAY
        strokeWidth = 2f
        isAntiAlias = true
    }

    private val textPaint = Paint().apply {
        color = Color.GRAY
        textSize = 25f
        isAntiAlias = true
    }

    private val coordinates = mutableListOf<Pair<Float, Float>>()

    fun setCoordinates(coords: List<Pair<Float, Float>>) {
        coordinates.clear()
        coordinates.addAll(coords)
        invalidate()
    }

    public override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val maxXValue = 50
        val maxYValue = 100
        val stepX = maxXValue / 10
        val stepY = maxYValue / 10
        val scaleX = width.toFloat() / maxXValue
        val scaleY = height.toFloat() / maxYValue

        drawGrid(canvas, stepX, stepY, scaleX, scaleY)

        for (i in 0 until coordinates.size - 1) {
            val startX = coordinates[i].first * scaleX
            val startY = height - coordinates[i].second * scaleY
            val endX = coordinates[i + 1].first * scaleX
            val endY = height - coordinates[i + 1].second * scaleY
            canvas.drawLine(startX, startY, endX, endY, paint)
        }
    }

    private fun drawGrid(canvas: Canvas, stepX: Int, stepY: Int, scaleX: Float, scaleY: Float) {
        // Вертикальные линии
        for (i in 0..10) {
            val x = i * stepX * scaleX
            canvas.drawLine(x, height.toFloat(), x, 0f, gridPaint)
            canvas.drawText("${i * stepX}", x, height.toFloat() - 20, textPaint) // Подпись оси X
        }

        // Горизонтальные линии
        for (i in 0..10) {
            val y = i * stepY * scaleY
            canvas.drawLine(0f, height - y, width.toFloat(), height - y, gridPaint)
            canvas.drawText("${i * stepY}", 0f, height - y - 20, textPaint) // Подпись оси Y
        }
    }
}
