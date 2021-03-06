/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.immimu.pm.di.module

import com.immimu.pm.ProjectActivity
import com.immimu.pm.ProjectComposerActivity
import com.immimu.pm.SubTaskActivity
import com.immimu.pm.TaskComposerActivity
import com.immimu.pm.TaskExecutorActivity
import com.immimu.pm.TaskListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

  @ContributesAndroidInjector
  internal abstract fun contributeProjectActivity(): ProjectActivity

  @ContributesAndroidInjector
  internal abstract fun contributeProjectComposerActivity(): ProjectComposerActivity

  @ContributesAndroidInjector(modules = [SubTaskFragmentModule::class])
  internal abstract fun contributeTaskListActivity(): TaskListActivity

  @ContributesAndroidInjector(modules = [SubTaskFragmentModule::class])
  internal abstract fun contributeTaskDetailActivity(): SubTaskActivity

  @ContributesAndroidInjector
  internal abstract fun contributeTaskComposerActivity(): TaskComposerActivity

  @ContributesAndroidInjector
  internal abstract fun contributeTaskExecutorActivity(): TaskExecutorActivity

}
