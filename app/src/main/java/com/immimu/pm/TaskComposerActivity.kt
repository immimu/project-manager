package com.immimu.pm

import android.R.layout
import android.os.Bundle
import android.support.v4.app.Fragment
import android.widget.ArrayAdapter
import com.immimu.pm.context.EXTRA_IS_SUB_TASK
import com.immimu.pm.context.EXTRA_PROJECT_ID
import com.immimu.pm.entity.Priority
import com.immimu.pm.entity.SubTask
import com.immimu.pm.entity.Task
import com.immimu.pm.vm.ProjectViewModel
import dagger.android.AndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_task_composer.createProjectButton
import kotlinx.android.synthetic.main.activity_task_composer.prioritySpinner
import kotlinx.android.synthetic.main.activity_task_composer.projectDescEditText
import kotlinx.android.synthetic.main.activity_task_composer.projectNameEditText
import kotlinx.android.synthetic.main.activity_task_composer.targetEditText
import kotlinx.android.synthetic.main.activity_task_composer.toolbar
import javax.inject.Inject

class TaskComposerActivity : BaseActivity(), HasSupportFragmentInjector {

  @Inject
  lateinit var projectViewModel: ProjectViewModel

  private val projectId: Int
    get() = intent.getIntExtra(EXTRA_PROJECT_ID, 0)
  private val isSubTask: Boolean
    get() = intent.getBooleanExtra(EXTRA_IS_SUB_TASK, false)

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_task_composer)
    setSupportActionBar(toolbar)
    setUpActionBar()
    val priorityList = arrayOf("NONE", "LOW", "MEDIUM", "HIGH", "URGENT")
    val priorityListValue = arrayOf(Priority.NONE, Priority.LOW, Priority.MEDIUM, Priority.HIGH,
        Priority.URGENT)

    val adapter = ArrayAdapter(this, layout.simple_spinner_item, priorityList)
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
    prioritySpinner.adapter = adapter

    var validated = false
    createProjectButton.setOnClickListener {
      if (isSubTask) {
        val task = SubTask(projectId).apply {

          // handle project name
          val nameValue = projectNameEditText.text.toString()
          if (nameValue.isEmpty()) {
            projectNameEditText.error = getString(R.string.error_required)
          } else {
            projectNameEditText.error = null
            name = nameValue
          }

          // handle project description
          val descValue = projectDescEditText.text.toString()
          if (descValue.isEmpty()) {
            projectDescEditText.error = getString(R.string.error_required)
          } else {
            projectDescEditText.error = null
            description = descValue
          }

          priority = priorityListValue[prioritySpinner.selectedItemPosition]
          val targetString = targetEditText.text.toString()
          target = if (targetString.isNotEmpty()) {
            targetString.toInt()
          } else {
            0
          }

          if (nameValue.isNotEmpty() && descValue.isNotEmpty()) {
            validated = true
          }
        }
        if (validated) {
          projectViewModel.createSubTask(task)
          finish()
        }
      } else {
        val task = Task(projectId).apply {

          // handle project name
          val nameValue = projectNameEditText.text.toString()
          if (nameValue.isEmpty()) {
            projectNameEditText.error = getString(R.string.error_required)
          } else {
            projectNameEditText.error = null
            name = nameValue
          }

          // handle project description
          val descValue = projectDescEditText.text.toString()
          if (descValue.isEmpty()) {
            projectDescEditText.error = getString(R.string.error_required)
          } else {
            projectDescEditText.error = null
            description = descValue
          }

          priority = priorityListValue[prioritySpinner.selectedItemPosition]
          val targetString = targetEditText.text.toString()
          target = if (targetString.isNotEmpty()) {
            targetString.toInt()
          } else {
            0
          }

          if (nameValue.isNotEmpty() && descValue.isNotEmpty()) {
            validated = true
          }
        }
        if (validated) {
          projectViewModel.createTask(task)
          finish()
        }
      }
    }
  }

  private fun setUpActionBar() {
    val actionBar = supportActionBar
    actionBar?.let {
      it.setDisplayHomeAsUpEnabled(true)
      it.setDisplayShowHomeEnabled(true)
    }
  }

  override fun supportFragmentInjector(): AndroidInjector<Fragment>? = null
}
