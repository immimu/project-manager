package com.immimu.pm

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.MenuItem
import com.immimu.pm.context.EXTRA_PARENT_TASK_ID
import com.immimu.pm.entity.Task
import com.immimu.pm.vm.ProjectViewModel
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_sub_task.detailToolbar
import javax.inject.Inject

/**
 * An activity representing a single Task detail screen. This
 * activity is only used on narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a [TaskListActivity].
 */
class SubTaskActivity : BaseActivity(), HasSupportFragmentInjector {

  @Inject
  lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>
  @Inject
  lateinit var projectViewModel: ProjectViewModel

  private val parentTaskId: Int
    get() = intent.getIntExtra(EXTRA_PARENT_TASK_ID, 0)

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_sub_task)
    setSupportActionBar(detailToolbar)
    detailToolbar.title = title
    setUpActionBar()

    val task: Task? = projectViewModel.getTaskById(parentTaskId)
    task?.let { parentTask ->
      supportActionBar?.title = parentTask.name
    }

    // savedInstanceState is non-null when there is fragment state
    // saved from previous configurations of this activity
    // (e.g. when rotating the screen from portrait to landscape).
    // In this case, the fragment will automatically be re-added
    // to its container so we don't need to manually add it.
    // For more information, see the Fragments API guide at:
    //
    // http://developer.android.com/guide/components/fragments.html
    //
    if (savedInstanceState == null) {
      // Create the detail fragment and add it to the activity
      // using a fragment transaction.
      val fragment = SubTaskFragment().apply {
        arguments = Bundle().apply {
          putInt(EXTRA_PARENT_TASK_ID, parentTaskId)
        }
      }

      supportFragmentManager.beginTransaction()
          .add(R.id.taskDetailContainer, fragment)
          .commit()
    }
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

  override fun supportFragmentInjector(): AndroidInjector<Fragment> = dispatchingAndroidInjector
}
