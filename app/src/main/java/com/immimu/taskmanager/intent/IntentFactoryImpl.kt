package com.immimu.taskmanager.intent

import android.content.Context
import android.content.Intent
import com.immimu.taskmanager.ProjectActivity
import com.immimu.taskmanager.ProjectComposerActivity
import com.immimu.taskmanager.TaskDetailActivity
import com.immimu.taskmanager.TaskListActivity

class IntentFactoryImpl : IntentFactory{

  override fun createProjectScreen(context: Context): Intent = Intent(context,ProjectActivity::class.java)

  override fun createProjectComposerScreen(context: Context): Intent = Intent(context,ProjectComposerActivity::class.java)

  override fun createTaskScreen(context: Context): Intent = Intent(context,TaskListActivity::class.java)

  override fun createTaskDetailsScreen(context: Context): Intent = Intent(context,TaskDetailActivity::class.java)

}