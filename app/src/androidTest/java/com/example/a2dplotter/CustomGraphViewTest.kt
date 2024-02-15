package com.example.a2dplotter

import android.graphics.Canvas
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

@RunWith(AndroidJUnit4::class)
class CustomGraphViewTest {

    private lateinit var customGraphView: CustomGraphView

    @Before
    fun setup() {
        customGraphView = CustomGraphView(ApplicationProvider.getApplicationContext(), null)
    }

    @Test
    fun testOnDraw() {
        val canvas = mock(Canvas::class.java)

        // Задаем некоторые координаты
        val coordinates = mutableListOf(
            Pair(0.0f, 0.0f),
            Pair(10.0f, 20.0f),
            Pair(20.0f, 40.0f),
            Pair(30.0f, 80.0f),
            Pair(40.0f, 60.0f),
            Pair(50.0f, 90.0f)
        )
        customGraphView.setCoordinates(coordinates)

        // Ожидаемые значения для сетки
        val expectedStepX = 5
        val expectedStepY = 10
        val expectedScaleX = customGraphView.width.toFloat() / 50
        val expectedScaleY = customGraphView.height.toFloat() / 100

        // Вызываем метод onDraw
        customGraphView.onDraw(canvas)

        // Проверяем отрисовку сетки
        //verify(canvas).drawLine(0f, customGraphView.height.toFloat(), customGraphView.width.toFloat(), customGraphView.height.toFloat(), customGraphView.gridPaint)
        //verify(canvas).drawLine(0f, customGraphView.height.toFloat(), 0f, 0f, customGraphView.gridPaint)

        // Проверяем отрисовку линий графика
        for (i in 0 until coordinates.size - 1) {
            val startX = coordinates[i].first * expectedScaleX
            val startY = customGraphView.height.toFloat() - coordinates[i].second * expectedScaleY
            val endX = coordinates[i + 1].first * expectedScaleX
            val endY = customGraphView.height.toFloat() - coordinates[i + 1].second * expectedScaleY
            verify(canvas).drawLine(startX, startY, endX, endY, customGraphView.paint)
        }
    }
}
