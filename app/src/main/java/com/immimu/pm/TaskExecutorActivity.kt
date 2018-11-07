package com.immimu.pm

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.MenuItem
import com.afollestad.materialdialogs.MaterialDialog
import com.immimu.pm.context.EXTRA_IS_SUB_TASK
import com.immimu.pm.context.EXTRA_TASK_ID
import com.immimu.pm.entity.AbstractTask
import com.immimu.pm.entity.Status.DONE
import com.immimu.pm.entity.Status.IN_PROGRESS
import com.immimu.pm.entity.Status.TODO
import com.immimu.pm.entity.SubTask
import com.immimu.pm.entity.Task
import com.immimu.pm.vm.ProjectViewModel
import dagger.android.AndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_task_executor.leftTimeTextView
import kotlinx.android.synthetic.main.activity_task_executor.startButton
import kotlinx.android.synthetic.main.activity_task_executor.targetTextView
import kotlinx.android.synthetic.main.activity_task_executor.timeTextView
import kotlinx.android.synthetic.main.activity_task_executor.toolbar
import org.joda.time.DateTime
import java.util.Date
import javax.inject.Inject

class TaskExecutorActivity : BaseActivity(), HasSupportFragmentInjector {

  @Inject
  lateinit var projectViewModel: ProjectViewModel

  private val id: Int
    get() = intent.getIntExtra(EXTRA_TASK_ID, 0)
  private val isSubTaks: Boolean
    get() = intent.getBooleanExtra(EXTRA_IS_SUB_TASK, false)
  private var target = 0
  private var inProgress = false

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

    inProgress = task.status == IN_PROGRESS

    bindTask(task)
    startButton.setOnClickListener {
      if (inProgress) {
        showDialogConfirmation(task)
      } else {
        task.startDate = Date()
        task.status = IN_PROGRESS
        if (task is Task) {
          projectViewModel.updateTask(task)
        } else if (task is SubTask) {
          projectViewModel.updateSubTask(task)
        }
      }
    }
  }

  private fun bindTask(task: AbstractTask) {
    target = task.target
    val startDate = DateTime(task.startDate)
    val finish = startDate.plusHours(2)
    val timeLeft = finish.minusMillis(DateTime.now().millisOfDay)
    supportActionBar?.title = task.name
    targetTextView.text = resources.getQuantityString(R.plurals.target_time, target,
        target)
    leftTimeTextView.text = String.format(resources.getString(R.string.left_time),
        timeLeft.toString("HH:mm:ss"), timeLeft)
    timeTextView.text = String.format(resources.getString(R.string.time_format),
        finish.toString("HH:mm:ss"))

    if (task.status == TODO) {
      startButton.text = resources.getString(R.string.button_start_task)
    } else {
      startButton.text = resources.getString(R.string.button_stop)
    }
  }

  private fun showDialogConfirmation(task: AbstractTask) {
    MaterialDialog.Builder(this)
        .title(getString(R.string.title_confirmation))
        .content(getString(R.string.text_task_confirmation))
        .positiveText(getString(R.string.button_yes))
        .negativeText(getString(R.string.button_no))
        .onPositive { _, _ ->
          task.status = DONE
          if (task is Task) {
            projectViewModel.updateTask(task)
          } else if (task is SubTask) {
            projectViewModel.updateSubTask(task)
          }
          finish()
        }
        .onNegative { dialog, _ -> dialog.dismiss() }.show()
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
