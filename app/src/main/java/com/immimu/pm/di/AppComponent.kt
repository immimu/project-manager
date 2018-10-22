package com.immimu.pm.di

import android.app.Application
import com.immimu.pm.context.TaskManagerApplication
import com.immimu.pm.di.module.ActivityModule
import com.immimu.pm.di.module.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [(AndroidInjectionModule::class), (AppModule::class), (ActivityModule::class)])
interface AppComponent {

  fun inject(taskManagerApplication: TaskManagerApplication)

  @Component.Builder
  interface Builder {
    @BindsInstance
    fun application(application: Application): Builder

    fun build(): AppComponent
  }
}