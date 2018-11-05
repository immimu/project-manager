package com.immimu.pm.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.immimu.pm.R
import com.immimu.pm.entity.SubTask
import kotlinx.android.synthetic.main.task_list_content.view.createdAtTextView
import kotlinx.android.synthetic.main.task_list_content.view.moreMenu
import kotlinx.android.synthetic.main.task_list_content.view.taskDescTextView
import kotlinx.android.synthetic.main.task_list_content.view.taskNameTextView
import kotlinx.android.synthetic.main.task_list_content.view.viewTaskButton
import java.text.SimpleDateFormat
import java.util.Locale

class SubTaskAdapter :
    RecyclerView.Adapter<SubTaskAdapter.ViewHolder>() {

  val values: MutableList<SubTask> = ArrayList()
  private val simpleDateFormat = SimpleDateFormat("dd MMM yyyy HH:mm:ss", Locale.US)
  var subTaskItemListener: SubTaskItemListener? = null

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val view = LayoutInflater.from(parent.context)
        .inflate(R.layout.task_list_content, parent, false)
    return ViewHolder(view)
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val item = values[position]
    holder.projectName.text = item.name
    holder.projectDesc.text = item.description
    holder.createdAt.text = holder.itemView.context.getString(R.string.text_created_at).plus(
        " ").plus(
        simpleDateFormat.format(item.createdAt))


    holder.viewButton.text = holder.itemView.context.getString(R.string.button_start_task)

    holder.viewButton.setOnClickListener {
      subTaskItemListener?.onSubTaskClicked(item)
    }

    holder.moreMenu.setOnClickListener {
      subTaskItemListener?.onSubTaskMoreMenuClicked(it, item)
    }
  }

  override fun getItemCount() = values.size

  inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val projectName: TextView = view.taskNameTextView
    val projectDesc: TextView = view.taskDescTextView
    val createdAt: TextView = view.createdAtTextView
    val viewButton: Button = view.viewTaskButton
    val moreMenu: ImageView = view.moreMenu
  }

  interface SubTaskItemListener {
    fun onSubTaskClicked(task: SubTask)

    fun onSubTaskMoreMenuClicked(view: View, task: SubTask)
  }
}