package com.immimu.taskmanager.intent

import android.content.Context
import android.content.Intent

interface IntentFactory{
  fun createProjectScreen(context: Context) : Intent

  fun createProjectComposerScreen(context: Context) : Intent

  fun createTaskScreen(context: Context) : Intent

  fun createTaskDetailsScreen(context: Context) : Intent
}