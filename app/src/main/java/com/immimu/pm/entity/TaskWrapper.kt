package com.immimu.pm.entity

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Relation

open class TaskWrapper {
  @Embedded
  var task: Task? = null

  @Relation(parentColumn = "id",
      entityColumn = "parentTaskId")
  var subTasks: MutableList<SubTask> = ArrayList()
}