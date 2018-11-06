package com.immimu.pm

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.PopupMenu
import android.view.ContextThemeWrapper
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.afollestad.materialdialogs.MaterialDialog
import com.immimu.pm.R.string
import com.immimu.pm.adapter.ProjectAdapter
import com.immimu.pm.adapter.ProjectAdapter.ProjectItemListener
import com.immimu.pm.adapter.ProjectItemDecoration
import com.immimu.pm.entity.Project
import com.immimu.pm.intent.IntentFactory
import com.immimu.pm.vm.ProjectViewModel
import dagger.android.AndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_project.projectList
import kotlinx.android.synthetic.main.activity_project.toolbar
import kotlinx.android.synthetic.main.empty_view.emptyContainer
import kotlinx.android.synthetic.main.empty_view.emptyTextView
import javax.inject.Inject

class ProjectActivity : AppCompatActivity(), HasSupportFragmentInjector, ProjectItemListener {

  @Inject
  lateinit var intentFactory: IntentFactory
  @Inject
  lateinit var projectViewModel: ProjectViewModel

  private val projectAdapter = ProjectAdapter()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_project)

    setSupportActionBar(toolbar)
    toolbar.title = getString(R.string.title_project)

    emptyTextView.text = getString(string.text_empty_project)
    emptyTextView.setOnClickListener { createProject() }

    setupRecyclerView()
    projectViewModel.allProject.observe(this, Observer { items ->
      if (items != null && items.isNotEmpty()) {
        emptyContainer.visibility = View.GONE
        projectAdapter.values.clear()
        projectAdapter.values.addAll(items)
        projectAdapter.notifyDataSetChanged()
      } else {
        projectAdapter.values.clear()
        projectAdapter.notifyDataSetChanged()
        emptyContainer.visibility = View.VISIBLE
      }
    })
  }

  private fun setupRecyclerView() {
    val layoutManager: LinearLayoutManager? = LinearLayoutManager(this)
    projectList.layoutManager = layoutManager
    projectList.adapter = projectAdapter
    projectList.addItemDecoration(
        ProjectItemDecoration(resources.getDimensionPixelSize(R.dimen.margin_16dp)))
    projectAdapter.projectItemListener = this
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.project_menu, menu)
    return super.onCreateOptionsMenu(menu)
  }

  override fun onOptionsItemSelected(item: MenuItem?): Boolean = when (item?.itemId) {
    R.id.action_create -> {
      createProject()
      true
    }
    else -> super.onContextItemSelected(item)
  }

  private fun createProject() {
    startActivity(intentFactory.createProjectComposerScreen(this, true))
  }

  override fun onItemClicked(project: Project) {
    startActivity(intentFactory.createTaskScreen(this, project.id))
  }

  override fun onMoreMenuClicked(view: View, project: Project) {
    val wrapper = ContextThemeWrapper(this, R.style.MyPopupMenu)
    val popupMenu = PopupMenu(wrapper, view)
    val inflater = popupMenu.menuInflater
    inflater.inflate(R.menu.project_item_menu, popupMenu.menu)
    popupMenu.setOnMenuItemClickListener { item ->
      when (item.itemId) {
        R.id.action_delete -> {
          showDialogConfirmation(project)
          true
        }
        R.id.action_edit -> {
          startActivity(intentFactory.createProjectComposerScreen(this, false, project.id))
          true
        }
        R.id.action_add_task -> {
          startActivity(intentFactory.createTaskComposerScreen(this, project.id, false))
          true
        }
        else ->
          false
      }
    }
    popupMenu.show()
  }

  private fun showDialogConfirmation(project: Project) {
    MaterialDialog.Builder(this)
        .title(getString(string.title_delete_confirmation))
        .content(getString(string.text_delete_message))
        .positiveText(getString(string.button_yes))
        .negativeText(getString(string.button_no))
        .onPositive { _, _ -> projectViewModel.deleteProject(project) }
        .onNegative { dialog, _ -> dialog.dismiss() }.show()
  }

  override fun supportFragmentInjector(): AndroidInjector<Fragment>? = null
}
