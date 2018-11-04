package com.immimu.pm.di.module

import com.immimu.pm.SubTasklFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class TaskDetailFragmentModule {

  @ContributesAndroidInjector
  internal abstract fun contributeTaskDetailFragment(): SubTasklFragment

}