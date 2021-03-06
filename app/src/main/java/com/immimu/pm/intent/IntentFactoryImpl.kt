package com.immimu.pm.intent

import android.content.Context
import android.content.Intent
import com.immimu.pm.ProjectActivity
import com.immimu.pm.ProjectComposerActivity
import com.immimu.pm.SubTaskActivity
import com.immimu.pm.TaskComposerActivity
import com.immimu.pm.TaskExecutorActivity
import com.immimu.pm.TaskListActivity
import com.immimu.pm.context.EXTRA_IS_NEW
import com.immimu.pm.context.EXTRA_IS_SUB_TASK
import com.immimu.pm.context.EXTRA_PARENT_TASK_ID
import com.immimu.pm.context.EXTRA_PROJECT_ID
import com.immimu.pm.context.EXTRA_TASK_ID

class IntentFactoryImpl : IntentFactory {

  override fun createTaskExecutorScreen(context: Context, isSubTask: Boolean, taskId: Int): Intent {
    val intent = Intent(context, TaskExecutorActivity::class.java)
    intent.putExtra(EXTRA_IS_SUB_TASK, isSubTask)
    intent.putExtra(EXTRA_TASK_ID, taskId)
    return intent
  }

  override fun createProjectScreen(context: Context): Intent = Intent(context,
      ProjectActivity::class.java)

  override fun createProjectComposerScreen(context: Context, isNew: Boolean,
      projectId: Int?): Intent {
    val intent = Intent(context,
        ProjectComposerActivity::class.java)
    intent.putExtra(EXTRA_PROJECT_ID, projectId)
    intent.putExtra(EXTRA_IS_NEW, isNew)
    return intent
  }

  override fun createTaskScreen(context: Context, projectId: Int): Intent {
    val intent = Intent(context, TaskListActivity::class.java)
    intent.putExtra(EXTRA_PROJECT_ID, projectId)
    return intent
  }

  override fun createSubTaskScreen(context: Context, parentTaskId: Int): Intent {
    val intent = Intent(context,
        SubTaskActivity::class.java)
    intent.putExtra(EXTRA_PARENT_TASK_ID, parentTaskId)
    return intent
  }

  override fun createTaskComposerScreen(context: Context, projectId: Int,
      isSubTask: Boolean, isNew: Boolean, id: Int?): Intent {
    val intent = Intent(context, TaskComposerActivity::class.java)
    intent.putExtra(EXTRA_PROJECT_ID, projectId)
    intent.putExtra(EXTRA_IS_SUB_TASK, isSubTask)
    intent.putExtra(EXTRA_IS_NEW, isNew)
    intent.putExtra(EXTRA_TASK_ID, id)
    return intent
  }
}