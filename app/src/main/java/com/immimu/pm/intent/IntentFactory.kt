package com.immimu.pm.intent

import android.content.Context
import android.content.Intent

interface IntentFactory {
  fun createProjectScreen(context: Context): Intent

  fun createProjectComposerScreen(context: Context): Intent

  fun createTaskScreen(context: Context, projectId: Int): Intent

  fun createTaskDetailsScreen(context: Context): Intent
}