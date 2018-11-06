package com.immimu.pm.intent

import android.content.Context
import android.content.Intent

interface IntentFactory {
  fun createProjectScreen(context: Context): Intent

  fun createProjectComposerScreen(context: Context, isNew: Boolean, projectId: Int? = null): Intent

  fun createTaskScreen(context: Context, projectId: Int): Intent

  fun createSubTaskScreen(context: Context, parentTaskId: Int): Intent

  fun createTaskComposerScreen(context: Context, projectId: Int, isSubTask: Boolean,
      isNew: Boolean, id: Int? = null): Intent
}