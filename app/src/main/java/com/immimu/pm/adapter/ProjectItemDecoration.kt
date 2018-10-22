package com.immimu.pm.adapter

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.ItemDecoration
import android.support.v7.widget.RecyclerView.State
import android.view.View

class ProjectItemDecoration(private val margin: Int) : ItemDecoration() {

  override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: State) {
    val position = parent.getChildLayoutPosition(view)
    //left,top,right,bottom
    if (position == 0) {
      outRect.set(margin, margin, margin, margin)
    } else {
      outRect.set(margin, 0, margin, margin)
    }
  }
}