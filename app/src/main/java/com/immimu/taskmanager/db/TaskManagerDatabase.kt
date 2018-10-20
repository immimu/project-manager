package com.immimu.taskmanager.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.immimu.taskmanager.db.converter.DateConverter
import com.immimu.taskmanager.db.converter.PriorityConverter
import com.immimu.taskmanager.db.converter.StatusConverter
import com.immimu.taskmanager.db.converter.TimeUnitConverter
import com.immimu.taskmanager.db.dao.ProjectDao
import com.immimu.taskmanager.db.dao.TaskDao
import com.immimu.taskmanager.entity.Project
import com.immimu.taskmanager.entity.SubTask
import com.immimu.taskmanager.entity.Task

/**
 * Created by miftahmubarak on 9/22/17.
 */
@Database(entities = [(Project::class), (Task::class), (SubTask::class)],
    version = 1, exportSchema = false)
@TypeConverters(
    DateConverter::class, StatusConverter::class, PriorityConverter::class,
    TimeUnitConverter::class)
abstract class TaskManagerDatabase : RoomDatabase() {

  abstract fun projectDao(): ProjectDao

  abstract fun taskDao(): TaskDao
}
