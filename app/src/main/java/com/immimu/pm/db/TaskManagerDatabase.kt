package com.immimu.pm.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.immimu.pm.db.converter.DateConverter
import com.immimu.pm.db.converter.PriorityConverter
import com.immimu.pm.db.converter.StatusConverter
import com.immimu.pm.db.converter.TimeUnitConverter
import com.immimu.pm.db.dao.ProjectDao
import com.immimu.pm.db.dao.TaskDao
import com.immimu.pm.entity.Project
import com.immimu.pm.entity.SubTask
import com.immimu.pm.entity.Task

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
