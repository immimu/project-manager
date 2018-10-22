package com.immimu.pm.intent

import android.content.Context
import android.content.Intent
import com.immimu.pm.ProjectActivity
import com.immimu.pm.ProjectComposerActivity
import com.immimu.pm.TaskDetailActivity
import com.immimu.pm.TaskListActivity
import com.immimu.pm.context.EXTRA_PROJECT_ID

class IntentFactoryImpl : IntentFactory {

  override fun createProjectScreen(context: Context): Intent = Intent(context,
      ProjectActivity::class.java)

  override fun createProjectComposerScreen(context: Context): Intent = Intent(context,
      ProjectComposerActivity::class.java)

  override fun createTaskScreen(context: Context, projectId: Int): Intent {
    val intent = Intent(context, TaskListActivity::class.java)
    intent.putExtra(EXTRA_PROJECT_ID, projectId)
    return intent
  }

  override fun createTaskDetailsScreen(context: Context): Intent = Intent(context,
      TaskDetailActivity::class.java)

}