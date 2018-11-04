package com.immimu.pm

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.immimu.pm.intent.IntentFactory
import dagger.android.AndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_task_detail.detailToolbar
import javax.inject.Inject

/**
 * An activity representing a single Task detail screen. This
 * activity is only used on narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a [TaskListActivity].
 */
class SubTaskActivity : AppCompatActivity(), HasSupportFragmentInjector {

  @Inject
  lateinit var intentFactory: IntentFactory

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_task_detail)
    setSupportActionBar(detailToolbar)

    // Show the Up button in the action bar.
    supportActionBar?.setDisplayHomeAsUpEnabled(true)

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
      val fragment = SubTasklFragment().apply {
        arguments = Bundle().apply {
          putString(SubTasklFragment.ARG_ITEM_ID,
              intent.getStringExtra(SubTasklFragment.ARG_ITEM_ID))
        }
      }

      supportFragmentManager.beginTransaction()
          .add(R.id.taskDetailContainer, fragment)
          .commit()
    }
  }

  override fun onOptionsItemSelected(item: MenuItem) =
      when (item.itemId) {
        android.R.id.home -> {
          // This ID represents the Home or Up button. In the case of this
          // activity, the Up button is shown. For
          // more details, see the Navigation pattern on Android Design:
          //
          // http://developer.android.com/design/patterns/navigation.html#up-vs-back

          navigateUpTo(Intent(this, TaskListActivity::class.java))
          true
        }
        else -> super.onOptionsItemSelected(item)
      }

  override fun supportFragmentInjector(): AndroidInjector<Fragment>? = null
}
