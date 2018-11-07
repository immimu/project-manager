package com.immimu.pm.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.immimu.pm.R
import com.immimu.pm.entity.Priority.HIGH
import com.immimu.pm.entity.Priority.LOW
import com.immimu.pm.entity.Priority.MEDIUM
import com.immimu.pm.entity.Priority.URGENT
import com.immimu.pm.entity.Status
import com.immimu.pm.entity.Status.DONE
import com.immimu.pm.entity.TaskWrapper
import kotlinx.android.synthetic.main.task_list_content.view.createdAtTextView
import kotlinx.android.synthetic.main.task_list_content.view.moreMenu
import kotlinx.android.synthetic.main.task_list_content.view.priorityTextView
import kotlinx.android.synthetic.main.task_list_content.view.statusTextView
import kotlinx.android.synthetic.main.task_list_content.view.targetTextView
import kotlinx.android.synthetic.main.task_list_content.view.taskDescTextView
import kotlinx.android.synthetic.main.task_list_content.view.taskNameTextView
import kotlinx.android.synthetic.main.task_list_content.view.viewTaskButton
import java.text.SimpleDateFormat
import java.util.Locale

class TaskAdapter :
    RecyclerView.Adapter<TaskAdapter.ViewHolder>() {

  val values: MutableList<TaskWrapper> = ArrayList()
  private val simpleDateFormat = SimpleDateFormat("dd MMM yyyy HH:mm:ss", Locale.US)
  var taskItemListener: TaskItemListener? = null

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val view = LayoutInflater.from(parent.context)
        .inflate(R.layout.task_list_content, parent, false)
    return ViewHolder(view)
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val item = values[position]
    holder.projectName.text = item.task?.name
    holder.projectDesc.text = item.task?.description
    holder.createdAt.text = holder.itemView.context.getString(R.string.text_created_at).plus(
        " ").plus(
        simpleDateFormat.format(item.task?.createdAt))

    val status = when (item.task?.status) {
      Status.TODO -> "TO DO"
      Status.IN_PROGRESS -> "IN PROGRESS"
      else -> "DONE"
    }

    val priority = when (item.task?.priority) {
      LOW -> "LOW"
      MEDIUM -> "MEDIUM"
      HIGH -> "HIGH"
      URGENT -> "URGENT"
      else -> "NONE"
    }

    holder.status.text = status
    holder.priority.text = priority
    holder.target.text = item.task?.target.toString().plus(" Hours")

    if (item.subTasks.size > 0) {
      holder.viewButton.text = holder.itemView.context.getString(R.string.button_view_sub_task)
      holder.target.visibility = View.INVISIBLE
    } else {
      holder.viewButton.text = holder.itemView.context.getString(R.string.button_start_task)
      holder.target.visibility = View.VISIBLE
    }

    if (item.task?.status == DONE) {
      holder.viewButton.visibility = View.INVISIBLE
    } else {
      holder.viewButton.visibility = View.VISIBLE
    }

    holder.viewButton.setOnClickListener {
      taskItemListener?.onTaskClicked(item)
    }

    holder.moreMenu.setOnClickListener {
      taskItemListener?.onTaskMoreMenuClicked(it, item)
    }
  }

  override fun getItemCount() = values.size

  inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val projectName: TextView = view.taskNameTextView
    val projectDesc: TextView = view.taskDescTextView
    val createdAt: TextView = view.createdAtTextView
    val viewButton: Button = view.viewTaskButton
    val moreMenu: ImageView = view.moreMenu
    val status: TextView = view.statusTextView
    val priority: TextView = view.priorityTextView
    val target: TextView = view.targetTextView
  }

  interface TaskItemListener {

    fun onTaskClicked(task: TaskWrapper)

    fun onTaskMoreMenuClicked(view: View, task: TaskWrapper)
  }
}