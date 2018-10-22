package com.immimu.pm

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.immimu.pm.entity.DummyContent
import com.immimu.pm.entity.Task
import dagger.android.AndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_task_list.fab
import kotlinx.android.synthetic.main.activity_task_list.toolbar
import kotlinx.android.synthetic.main.task_list.task_detail_container
import kotlinx.android.synthetic.main.task_list.task_list
import kotlinx.android.synthetic.main.task_list_content.view.content
import kotlinx.android.synthetic.main.task_list_content.view.id_text

class TaskListActivity : BaseActivity(), HasSupportFragmentInjector {

  /**
   * Whether or not the activity is in two-pane mode, i.e. running on a tablet
   * device.
   */
  private var twoPane: Boolean = false

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_task_list)

    setSupportActionBar(toolbar)
    toolbar.title = title
    setUpActionBar()

    fab.setOnClickListener { view ->
      Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
          .setAction("Action", null).show()
    }

    if (task_detail_container != null) {
      // The detail container view will be present only in the
      // large-screen layouts (res/values-w900dp).
      // If this view is present, then the
      // activity should be in two-pane mode.
      twoPane = true
    }

    setupRecyclerView(task_list)
  }

  private fun setUpActionBar() {
    val actionBar = supportActionBar
    actionBar?.let {
      it.setDisplayHomeAsUpEnabled(true)
      it.setDisplayShowHomeEnabled(true)
    }
  }

  private fun setupRecyclerView(recyclerView: RecyclerView) {
    recyclerView.adapter = SimpleItemRecyclerViewAdapter(this, DummyContent.ITEMS, twoPane)
  }

  class SimpleItemRecyclerViewAdapter(private val parentActivity: TaskListActivity,
      private val values: List<Task>,
      private val twoPane: Boolean) :
      RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder>() {

    private val onClickListener: View.OnClickListener

    init {
      onClickListener = View.OnClickListener { v ->
        val item = v.tag as Task
        if (twoPane) {
          val fragment = TaskDetailFragment().apply {
            arguments = Bundle().apply {
              putInt(TaskDetailFragment.ARG_ITEM_ID, item.id)
            }
          }
          parentActivity.supportFragmentManager
              .beginTransaction()
              .replace(R.id.task_detail_container, fragment)
              .commit()
        } else {
          val intent = Intent(v.context, TaskDetailActivity::class.java).apply {
            putExtra(TaskDetailFragment.ARG_ITEM_ID, item.id)
          }
          v.context.startActivity(intent)
        }
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

  override fun supportFragmentInjector(): AndroidInjector<Fragment>? = null
}
