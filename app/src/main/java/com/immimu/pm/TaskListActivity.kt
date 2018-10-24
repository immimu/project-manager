package com.immimu.pm

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.immimu.pm.adapter.AbstractTaskAdapter
import com.immimu.pm.context.EXTRA_PROJECT_ID
import com.immimu.pm.entity.Task
import com.immimu.pm.intent.IntentFactory
import com.immimu.pm.vm.ProjectViewModel
import dagger.android.AndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_task_list.toolbar
import kotlinx.android.synthetic.main.empty_view.emptyContainer
import kotlinx.android.synthetic.main.empty_view.emptyTextView
import kotlinx.android.synthetic.main.task_list.task_detail_container
import kotlinx.android.synthetic.main.task_list.task_list
import javax.inject.Inject

class TaskListActivity : BaseActivity(), HasSupportFragmentInjector {

  @Inject
  lateinit var projectViewModel: ProjectViewModel
  @Inject
  lateinit var intentFactory: IntentFactory

  private var twoPane: Boolean = false
  private val taskAdapter = AbstractTaskAdapter<Task>()
  private val projectId: Int
    get() = intent.getIntExtra(EXTRA_PROJECT_ID, 0)

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_task_list)

    setSupportActionBar(toolbar)
    toolbar.title = title
    setUpActionBar()

    emptyTextView.text = getString(R.string.text_empty_task)
    emptyTextView.setOnClickListener { createTask() }

    if (task_detail_container != null) {
      twoPane = true
    }

    setupRecyclerView(task_list)
    projectViewModel.getAllTask(projectId).observe(this, Observer { items ->
      if (items != null && items.isNotEmpty()) {
        emptyContainer.visibility = View.GONE
        taskAdapter.values.clear()
        taskAdapter.values.addAll(items)
        taskAdapter.notifyDataSetChanged()
      } else {
        taskAdapter.values.clear()
        taskAdapter.notifyDataSetChanged()
        emptyContainer.visibility = View.VISIBLE
      }
    })
  }

  private fun createTask() {
    startActivity(intentFactory.createTaskComposerScreen(this, projectId))
  }

  private fun setUpActionBar() {
    val actionBar = supportActionBar
    actionBar?.let {
      it.setDisplayHomeAsUpEnabled(true)
      it.setDisplayShowHomeEnabled(true)
    }
  }

  private fun setupRecyclerView(recyclerView: RecyclerView) {
    recyclerView.adapter = taskAdapter
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.project_menu, menu)
    return super.onCreateOptionsMenu(menu)
  }

  override fun onOptionsItemSelected(item: MenuItem?): Boolean = when (item?.itemId) {
    android.R.id.home -> {
      onBackPressed()
      true
    }
    R.id.action_create -> {
      createTask()
      true
    }
    else -> super.onContextItemSelected(item)
  }

  override fun supportFragmentInjector(): AndroidInjector<Fragment>? = null
}
