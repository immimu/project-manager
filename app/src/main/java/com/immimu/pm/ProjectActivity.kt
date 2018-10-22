package com.immimu.pm

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.immimu.pm.adapter.ProjectAdapter
import com.immimu.pm.adapter.ProjectItemDecoration
import com.immimu.pm.intent.IntentFactory
import com.immimu.pm.vm.ProjectViewModel
import dagger.android.AndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_project.projectList
import kotlinx.android.synthetic.main.activity_project.toolbar
import javax.inject.Inject

class ProjectActivity : AppCompatActivity(), HasSupportFragmentInjector {

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

    setupRecyclerView()
    projectViewModel.allProject.observe(this, Observer { items ->
      if (items != null && items.isNotEmpty()) {
        projectAdapter.values.clear()
        projectAdapter.values.addAll(items)
        projectAdapter.notifyDataSetChanged()
      }
    })
  }

  private fun setupRecyclerView() {
    val layoutManager = LinearLayoutManager(this)
    projectList.layoutManager = layoutManager
    projectList.adapter = projectAdapter
    projectList.addItemDecoration(
        ProjectItemDecoration(resources.getDimensionPixelSize(R.dimen.margin_16dp)))
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.project_menu, menu)
    return super.onCreateOptionsMenu(menu)
  }

  override fun onOptionsItemSelected(item: MenuItem?): Boolean = when (item?.itemId) {
    R.id.action_create -> {
      startActivity(intentFactory.createProjectComposerScreen(this))
      true
    }
    else -> super.onContextItemSelected(item)
  }

  override fun supportFragmentInjector(): AndroidInjector<Fragment>? = null
}
