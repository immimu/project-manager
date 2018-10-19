package com.immimu.taskmanager

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.support.design.widget.Snackbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.immimu.taskmanager.dummy.DummyContent
import com.immimu.taskmanager.dummy.Project
import kotlinx.android.synthetic.main.activity_project.projectList
import kotlinx.android.synthetic.main.activity_task_list.*
import kotlinx.android.synthetic.main.task_list_content.view.*

/**
 * An activity representing a list of Pings. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a [TaskDetailActivity] representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
class ProjectActivity : AppCompatActivity() {


  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_project)

    setSupportActionBar(toolbar)
    toolbar.title = title

    fab.setOnClickListener { view ->
      Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
          .setAction("Action", null).show()
    }

    setupRecyclerView(projectList)
  }

  private fun setupRecyclerView(recyclerView: RecyclerView) {
    recyclerView.adapter = ProjectRecyclerViewAdapter(DummyContent.projectItems)
  }

  class ProjectRecyclerViewAdapter(
      private val values: List<Project>) :
      RecyclerView.Adapter<ProjectRecyclerViewAdapter.ViewHolder>() {

    private val onClickListener: View.OnClickListener

    init {
      onClickListener = View.OnClickListener { v ->
        val item = v.tag as Project
        val intent = Intent(v.context, TaskListActivity::class.java).apply {
          putExtra(TaskDetailFragment.ARG_ITEM_ID, item.id)
        }
        v.context.startActivity(intent)
      }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
      val view = LayoutInflater.from(parent.context)
          .inflate(R.layout.task_list_content, parent, false)
      return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
      val item = values[position]
      holder.idView.text = item.id.toString()
      holder.contentView.text = item.name

      with(holder.itemView) {
        tag = item
        setOnClickListener(onClickListener)
      }
    }

    override fun getItemCount() = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
      val idView: TextView = view.id_text
      val contentView: TextView = view.content
    }
  }
}
