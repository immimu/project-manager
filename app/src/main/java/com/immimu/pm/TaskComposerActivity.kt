package com.immimu.pm

import android.os.Bundle
import android.support.v4.app.Fragment
import com.immimu.pm.context.EXTRA_PROJECT_ID
import com.immimu.pm.entity.Task
import com.immimu.pm.vm.ProjectViewModel
import dagger.android.AndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_task_composer.createProjectButton
import kotlinx.android.synthetic.main.activity_task_composer.projectDescEditText
import kotlinx.android.synthetic.main.activity_task_composer.projectNameEditText
import kotlinx.android.synthetic.main.activity_task_composer.toolbar
import javax.inject.Inject

class TaskComposerActivity : BaseActivity(), HasSupportFragmentInjector {

  @Inject
  lateinit var projectViewModel: ProjectViewModel

  val projectId: Int
    get() = intent.getIntExtra(EXTRA_PROJECT_ID, 0)

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_task_composer)
    setSupportActionBar(toolbar)
    setUpActionBar()
    /*val unitList = arrayOf("HOUR", "DAYS", "WEEKS", "MONTH")
    val unitListValue = arrayOf(TimeUnit.HOUR, TimeUnit.DAYS, TimeUnit.WEEK, TimeUnit.MONTH)

    val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, unitList)
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
    unitSpinner.adapter = adapter*/
    var validated = false
    createProjectButton.setOnClickListener {
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

        if (nameValue.isNotEmpty() && descValue.isNotEmpty()) {
          validated = true
        }
      }
      if (validated) {
        projectViewModel.createTask(task)
        finish()
      }
    }

    /*val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    deadLineButton.setOnClickListener {
      val datePickerDialog = DatePickerDialog(this,
          OnDateSetListener { _, year, month, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            deadLineDate = calendar.time
            deadLineButton.text = DateTime(deadLineDate).toString(longDateFormat)
          }, year, month, day)

      datePickerDialog.show()
    }*/
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
