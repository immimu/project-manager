package com.immimu.pm.di.module

import com.immimu.pm.SubTaskFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class SubTaskFragmentModule {

  @ContributesAndroidInjector
  internal abstract fun contributeSubTasklFragment(): SubTaskFragment

}