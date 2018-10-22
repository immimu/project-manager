package com.immimu.pm.context

import android.app.Activity
import android.app.Application
import com.immimu.pm.di.AppInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import net.danlew.android.joda.JodaTimeAndroid
import javax.inject.Inject

class TaskManagerApplication :Application(), HasActivityInjector{

  @Inject
  lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

  override fun onCreate() {
    super.onCreate()
    AppInjector.init(this)
    JodaTimeAndroid.init(this)
  }

  override fun activityInjector(): DispatchingAndroidInjector<Activity> = dispatchingAndroidInjector

}