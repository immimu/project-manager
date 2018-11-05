package com.immimu.pm

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.PopupMenu
import android.support.v7.widget.RecyclerView
import android.view.ContextThemeWrapper
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.afollestad.materialdialogs.MaterialDialog
import com.immimu.pm.R.dimen
import com.immimu.pm.R.string
import com.immimu.pm.R.style
import com.immimu.pm.adapter.ProjectItemDecoration
import com.immimu.pm.adapter.TaskAdapter
import com.immimu.pm.adapter.TaskAdapter.TaskItemListener
import com.immimu.pm.context.EXTRA_PARENT_TASK_ID
import com.immimu.pm.context.EXTRA_PROJECT_ID
import com.immimu.pm.entity.Project
import com.immimu.pm.entity.Task
import com.immimu.pm.entity.TaskWrapper
import com.immimu.pm.intent.IntentFactory
import com.immimu.pm.vm.ProjectViewModel
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_task_list.toolbar
import kotlinx.android.synthetic.main.empty_view.emptyContainer
import kotlinx.android.synthetic.main.empty_view.emptyTextView
import kotlinx.android.synthetic.main.task_list.taskDetailContainer
import kotlinx.android.synthetic.main.task_list.taskList
import org.jetbrains.anko.toast
import javax.inject.Inject

class TaskListActivity : BaseActivity(), HasSupportFragmentInjector, TaskItemListener {

  @Inject
  lateinit var projectViewModel: ProjectViewModel
  @Inject
  lateinit var intentFactory: IntentFactory
  @Inject
  lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

  private var twoPane: Boolean = false
  private val taskAdapter = TaskAdapter()
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

    if (taskDetailContainer != null) {
      twoPane = true
    }

    val project: Project? = projectViewModel.getProjectById(projectId)
    project?.let {
      supportActionBar?.title = it.name
    }

    setupRecyclerView(taskList)
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
    startActivity(intentFactory.createTaskComposerScreen(this, projectId, false))
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
    taskAdapter.taskItemListener = this
    recyclerView.addItemDecoration(
        ProjectItemDecoration(resources.getDimensionPixelSize(dimen.margin_10dp)))
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

  override fun onTaskClicked(task: TaskWrapper) {
    if (twoPane) {
      task.task?.let {
        val fragment = SubTaskFragment().apply {
          arguments = Bundle().apply {
            putInt(EXTRA_PARENT_TASK_ID, it.id)
          }
        }
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.taskDetailContainer, fragment)
            .commit()
      }
    } else {
      task.task?.let {
        startActivity(intentFactory.createSubTaskScreen(this, it.id))
      }
    }
  }

  override fun onTaskMoreMenuClicked(view: View, task: TaskWrapper) {
    val wrapper = ContextThemeWrapper(this, style.MyPopupMenu)
    val popupMenu = PopupMenu(wrapper, view)
    val inflater = popupMenu.menuInflater
    inflater.inflate(R.menu.task_item_menu, popupMenu.menu)
    popupMenu.setOnMenuItemClickListener { item ->
      when (item.itemId) {
        R.id.action_delete -> {
          task.task?.let {
            showDialogConfirmation(it)
          }
          true
        }
        R.id.action_edit -> {
          toast("TODO : add edit function")
          true
        }
        R.id.action_add_sub_task -> {
          task.task?.let {
            startActivity(intentFactory.createTaskComposerScreen(this, it.id, true))
          }
          true
        }
        else ->
          false
      }
    }
    popupMenu.show()
  }

  private fun showDialogConfirmation(task: Task) {
    MaterialDialog.Builder(this)
        .title(getString(string.title_delete_confirmation))
        .content(getString(string.text_delete_message))
        .positiveText(getString(string.button_yes))
        .negativeText(getString(string.button_no))
        .onPositive { _, _ -> projectViewModel.deleteTask(task) }
        .onNegative { dialog, _ -> dialog.dismiss() }.show()
  }

  override fun supportFragmentInjector(): AndroidInjector<Fragment> = dispatchingAndroidInjector
}
