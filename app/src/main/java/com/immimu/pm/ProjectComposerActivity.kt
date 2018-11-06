package com.immimu.pm

import android.os.Bundle
import android.support.v4.app.Fragment
import com.immimu.pm.context.EXTRA_IS_NEW
import com.immimu.pm.context.EXTRA_PROJECT_ID
import com.immimu.pm.entity.Project
import com.immimu.pm.vm.ProjectViewModel
import dagger.android.AndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_project_composer.createProjectButton
import kotlinx.android.synthetic.main.activity_project_composer.projectDescEditText
import kotlinx.android.synthetic.main.activity_project_composer.projectNameEditText
import kotlinx.android.synthetic.main.activity_project_composer.toolbar
import javax.inject.Inject

class ProjectComposerActivity : BaseActivity(), HasSupportFragmentInjector {

  @Inject
  lateinit var projectViewModel: ProjectViewModel
  private val projectId: Int
    get() = intent.getIntExtra(EXTRA_PROJECT_ID, 0)
  private val isNew: Boolean
    get() = intent.getBooleanExtra(EXTRA_IS_NEW, false)

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_project_composer)
    setSupportActionBar(toolbar)
    setUpActionBar()

    if (isNew.not()) {
      val project = projectViewModel.getProjectById(projectId)
      projectNameEditText.setText(project.name)
      projectDescEditText.setText(project.description)
      createProjectButton.text = getString(R.string.button_update)
    }
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

        if (nameValue.isNotEmpty() && descValue.isNotEmpty()) {
          validated = true
        }
      }
      if (validated) {
        if (isNew) {
          projectViewModel.createProject(project)
        } else {
          project.id = projectId
          projectViewModel.updateProject(project)
        }
        finish()
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
