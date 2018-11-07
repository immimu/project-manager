package com.immimu.pm

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.MenuItem
import com.immimu.pm.context.EXTRA_IS_SUB_TASK
import com.immimu.pm.context.EXTRA_TASK_ID
import com.immimu.pm.entity.AbstractTask
import com.immimu.pm.vm.ProjectViewModel
import dagger.android.AndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_task_executor.leftTimeTextView
import kotlinx.android.synthetic.main.activity_task_executor.targetTextView
import kotlinx.android.synthetic.main.activity_task_executor.toolbar
import javax.inject.Inject

class TaskExecutorActivity : BaseActivity(), HasSupportFragmentInjector {

  @Inject
  lateinit var projectViewModel: ProjectViewModel

  private val id: Int
    get() = intent.getIntExtra(EXTRA_TASK_ID, 0)
  private val isSubTaks: Boolean
    get() = intent.getBooleanExtra(EXTRA_IS_SUB_TASK, false)

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_task_executor)
    setSupportActionBar(toolbar)
    toolbar.title = title
    setUpActionBar()

    val task = if (isSubTaks) {
      projectViewModel.getSubTaskById(id)
    } else {
      projectViewModel.getTaskById(id)
    }
    bindTask(task)
  }

  private fun bindTask(task: AbstractTask) {
    supportActionBar?.title = task.name
    targetTextView.text = resources.getQuantityString(R.plurals.target_time, task.target)
    leftTimeTextView.text = resources.getQuantityString(R.plurals.left_time, task.target * 60)
  }

  private fun setUpActionBar() {
    val actionBar = supportActionBar
    actionBar?.let {
      it.setDisplayHomeAsUpEnabled(true)
      it.setDisplayShowHomeEnabled(true)
    }
  }

  override fun onOptionsItemSelected(item: MenuItem?): Boolean = when (item?.itemId) {
    android.R.id.home -> {
      onBackPressed()
      true
    }
    else -> super.onContextItemSelected(item)
  }

  override fun supportFragmentInjector(): AndroidInjector<Fragment>? = null
}
