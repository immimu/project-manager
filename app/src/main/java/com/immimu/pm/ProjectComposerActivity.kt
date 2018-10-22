package com.immimu.pm

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import com.immimu.pm.entity.Project
import com.immimu.pm.entity.TimeUnit
import com.immimu.pm.vm.ProjectViewModel
import dagger.android.AndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_project_composer.createProjectButton
import kotlinx.android.synthetic.main.activity_project_composer.projectDescEditText
import kotlinx.android.synthetic.main.activity_project_composer.projectNameEditText
import kotlinx.android.synthetic.main.activity_project_composer.projectTargetEditText
import kotlinx.android.synthetic.main.activity_project_composer.unitSpinner
import kotlinx.android.synthetic.main.activity_task_list.toolbar
import javax.inject.Inject

class ProjectComposerActivity : AppCompatActivity(), HasSupportFragmentInjector {

  @Inject
  lateinit var projectViewModel: ProjectViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_project_composer)
    setSupportActionBar(toolbar)

    val unitList = arrayOf("HOUR", "DAYS", "WEEKS", "MONTH")
    val unitListValue = arrayOf(TimeUnit.HOUR, TimeUnit.DAYS, TimeUnit.WEEK, TimeUnit.MONTH)

    val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, unitList)
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
    unitSpinner.adapter = adapter
    var validated = false
    createProjectButton.setOnClickListener {
      val project = Project().apply {

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

        // handle project description
        val targetValue = projectTargetEditText.text.toString()
        if (targetValue.isEmpty()) {
          projectTargetEditText.error = getString(R.string.error_required)
        } else {
          projectTargetEditText.error = null
          target = targetValue.toInt()
        }

        if (nameValue.isNotEmpty() && descValue.isNotEmpty() && targetValue.isNotEmpty()) {
          validated = true
        }
        timeUnit = unitListValue[unitSpinner.selectedItemPosition]
      }
      if (validated) {
        projectViewModel.createProject(project)
        finish()
      }
    }
  }

  override fun supportFragmentInjector(): AndroidInjector<Fragment>? = null
}
