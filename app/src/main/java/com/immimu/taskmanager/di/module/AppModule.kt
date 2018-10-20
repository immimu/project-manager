package com.immimu.taskmanager.di.module

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import com.immimu.taskmanager.db.TaskManagerDatabase
import com.immimu.taskmanager.db.dao.ProjectDao
import com.immimu.taskmanager.db.dao.TaskDao
import com.immimu.taskmanager.intent.IntentFactory
import com.immimu.taskmanager.intent.IntentFactoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [(ViewModelModule::class)])
internal class AppModule{

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
  fun provideTaskDao(db: TaskManagerDatabase): TaskDao {
    return db.taskDao()
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