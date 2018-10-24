package com.immimu.pm.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.immimu.pm.R
import com.immimu.pm.entity.AbstractTask
import com.immimu.pm.entity.Task
import kotlinx.android.synthetic.main.task_list_content.view.content
import kotlinx.android.synthetic.main.task_list_content.view.id_text

class AbstractTaskAdapter<T : AbstractTask> :
    RecyclerView.Adapter<AbstractTaskAdapter<T>.ViewHolder>() {

  val values: MutableList<T> = ArrayList()
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val view = LayoutInflater.from(parent.context)
        .inflate(R.layout.task_list_content, parent, false)
    return ViewHolder(view)
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val item = values[position] as Task
    holder.idView.text = item.id.toString()
    holder.contentView.text = item.name
  }

  override fun getItemCount() = values.size

  inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val idView: TextView = view.id_text
    val contentView: TextView = view.content
  }
}