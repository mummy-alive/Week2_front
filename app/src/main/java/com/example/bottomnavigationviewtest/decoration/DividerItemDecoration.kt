package com.example.bottomnavigationviewtest.decoration

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class DividerItemDecoration(context: Context) : RecyclerView.ItemDecoration() {

    private val paint: Paint = Paint().apply {
        color = ContextCompat.getColor(context, android.R.color.darker_gray)
        strokeWidth = 2f
        style = Paint.Style.STROKE
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val left = parent.paddingLeft
        val right = parent.width - parent.paddingRight

        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)
            val params = child.layoutParams as RecyclerView.LayoutParams
            val top = child.bottom + params.bottomMargin
            val bottom = top + paint.strokeWidth.toInt()

            c.drawLine(left.toFloat(), top.toFloat(), right.toFloat(), top.toFloat(), paint)
        }
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        outRect.bottom = paint.strokeWidth.toInt()
    }
}
