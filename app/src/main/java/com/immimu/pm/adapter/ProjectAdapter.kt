package com.immimu.pm.adapter

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.immimu.pm.R
import com.immimu.pm.entity.Project
import com.immimu.pm.entity.ProjectWrapper
import kotlinx.android.synthetic.main.project_list_content.view.createdAtTextView
import kotlinx.android.synthetic.main.project_list_content.view.moreMenu
import kotlinx.android.synthetic.main.project_list_content.view.parentLayout
import kotlinx.android.synthetic.main.project_list_content.view.projectDescTextView
import kotlinx.android.synthetic.main.project_list_content.view.projectNameTextView
import kotlinx.android.synthetic.main.project_list_content.view.viewTaskButton
import java.text.SimpleDateFormat
import java.util.Locale

class ProjectAdapter :
    RecyclerView.Adapter<ProjectAdapter.ViewHolder>() {

  val values: MutableList<ProjectWrapper> = ArrayList()
  private val simpleDateFormat = SimpleDateFormat("dd MMM yyyy HH:mm:ss", Locale.US)
  var projectItemListener: ProjectItemListener? = null
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val view = LayoutInflater.from(parent.context)
        .inflate(R.layout.project_list_content, parent, false)
    return ViewHolder(view)
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val item = values[position]
    item.project?.let { project ->

      holder.projectName.text = project.name
      holder.projectDesc.text = project.description
      holder.createdAt.text = holder.itemView.context.getString(R.string.text_created_at).plus(
          " ").plus(
          simpleDateFormat.format(project.createdAt))

      val text = holder.itemView.context.getString(R.string.button_view_task).plus(
          " (${item.tasks.size})")
      if (item.tasks.size > 0) {
        holder.viewButton.text = text
      } else {
        holder.viewButton.text = holder.itemView.context.getString(R.string.button_view_task)
      }
      holder.viewButton.setOnClickListener {
        projectItemListener?.onItemClicked(project)
      }

      holder.parentLayout.setOnClickListener {
        projectItemListener?.onItemClicked(project)
      }

      holder.moreMenu.setOnClickListener {
        projectItemListener?.onMoreMenuClicked(it, project)
      }
    }
  }

  override fun getItemCount() = values.size

  inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val projectName: TextView = view.projectNameTextView
    val projectDesc: TextView = view.projectDescTextView
    val createdAt: TextView = view.createdAtTextView
    val viewButton: Button = view.viewTaskButton
    val parentLayout: CardView = view.parentLayout
    val moreMenu: ImageView = view.moreMenu
  }

  interface ProjectItemListener {
    fun onItemClicked(project: Project)

    fun onMoreMenuClicked(view: View, project: Project)
  }
}