package com.immimu.pm.di.module

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import com.immimu.pm.db.TaskManagerDatabase
import com.immimu.pm.db.dao.ProjectDao
import com.immimu.pm.db.dao.ProjectWrapperDao
import com.immimu.pm.db.dao.SubTaskDao
import com.immimu.pm.db.dao.TaskDao
import com.immimu.pm.db.dao.TaskWrapperDao
import com.immimu.pm.intent.IntentFactory
import com.immimu.pm.intent.IntentFactoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [(ViewModelModule::class)])
internal class AppModule {

  @Singleton
  @Provides
  fun provideTaskManagerDatabase(app: Application): TaskManagerDatabase {
    return Room.databaseBuilder(app, TaskManagerDatabase::class.java, "task_manager.db")
        .allowMainThreadQueries()
        .build()
  }

  @Singleton
  @Provides
  fun provideProjectDao(db: TaskManagerDatabase): ProjectDao {
    return db.projectDao()
  }

  @Singleton
  @Provides
  fun provideProjectWrapperDao(db: TaskManagerDatabase): ProjectWrapperDao {
    return db.projectWrapperDaoDao()
  }

  @Singleton
  @Provides
  fun provideTaskDao(db: TaskManagerDatabase): TaskDao {
    return db.taskDao()
  }

  @Singleton
  @Provides
  fun provideTaskWrapperDao(db: TaskManagerDatabase): TaskWrapperDao {
    return db.taskWrapperDao()
  }

  @Singleton
  @Provides
  fun provideSubTaskDao(db: TaskManagerDatabase): SubTaskDao {
    return db.subTaskDao()
  }

  @Provides
  fun provideContext(app: Application): Context {
    return app.applicationContext
  }

  @Provides
  fun provideIntentFactory(): IntentFactory {
    return IntentFactoryImpl()
  }
}