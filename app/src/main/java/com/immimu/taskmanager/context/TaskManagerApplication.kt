package com.immimu.taskmanager.context

import android.app.Activity
import android.app.Application
import com.immimu.taskmanager.di.AppInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class TaskManagerApplication :Application(), HasActivityInjector{

  @Inject
  lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

  override fun onCreate() {
    super.onCreate()
    /*CalligraphyConfig.initDefault(CalligraphyConfig.Builder().setDefaultFontPath(
        getString(R.string.museo_sans_rounded_regular)).setFontAttrId(R.attr.fontPath).build())*/
    AppInjector.init(this)
  }

  override fun activityInjector(): DispatchingAndroidInjector<Activity> = dispatchingAndroidInjector

}