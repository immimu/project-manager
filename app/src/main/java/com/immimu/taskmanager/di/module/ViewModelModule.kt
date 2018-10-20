package com.immimu.taskmanager.di.module

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.immimu.taskmanager.di.ViewModelKey
import com.immimu.taskmanager.vm.ProjectViewModel
import com.immimu.taskmanager.vm.TaskManagerViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class ViewModelModule {

  @Binds
  @IntoMap
  @ViewModelKey(ProjectViewModel::class)
  internal abstract fun bindProjectViewModel(suratTitleViewModel: ProjectViewModel): ViewModel

  @Binds
  internal abstract fun bindViewModelFactory(
      factory: TaskManagerViewModelFactory): ViewModelProvider.Factory
}
