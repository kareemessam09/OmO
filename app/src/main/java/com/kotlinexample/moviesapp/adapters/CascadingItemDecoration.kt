package com.kotlinexample.moviesapp.adapters

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CascadingItemDecoration(context: Context) : LinearLayoutManager(context, HORIZONTAL, false) {

    override fun onLayoutCompleted(state: RecyclerView.State?) {
        super.onLayoutCompleted(state)
        scaleAndElevateChildViews()
    }

    override fun scrollHorizontallyBy(dx: Int, recycler: RecyclerView.Recycler?, state: RecyclerView.State?): Int {
        val scrolled = super.scrollHorizontallyBy(dx, recycler, state)
        scaleAndElevateChildViews()
        return scrolled
    }

    private fun scaleAndElevateChildViews() {
        val midPoint = width / 2.0f
        val d0 = 0.0f
        val d1 = 0.9f * midPoint
        val s0 = 1.0f
        val s1 = 1.2f
        val z0 = 0.0f
        val z1 = 6.0f

        for (i in 0 until childCount) {
            val child = getChildAt(i)
            val childMidPoint = (getDecoratedLeft(child!!) + getDecoratedRight(child)) / 2.0f
            val d = Math.min(d1, Math.abs(midPoint - childMidPoint))
            val scaleFactor = s0 + (s1 - s0) * (d1 - d) / (d1 - d0)
            val elevationFactor = z0 + (z1 - z0) * (d1 - d) / (d1 - d0)

            // Apply scale
            child.scaleX = scaleFactor
            child.scaleY = scaleFactor

            // Apply elevation
            child.translationZ = elevationFactor
        }
    }
}