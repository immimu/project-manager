package com.immimu.pm.entity

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Relation

open class ProjectWrapper {
  @Embedded
  var project: Project? = null

  @Relation(parentColumn = "id",
      entityColumn = "projectId")

  var tasks: MutableList<Task> = ArrayList()
}