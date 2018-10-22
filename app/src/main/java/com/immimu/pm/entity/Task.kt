package com.immimu.pm.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey
import com.immimu.pm.entity.Priority.NONE

@Entity
@ForeignKey(entity = Project::class, parentColumns = ["id"],
    childColumns = ["projectId"])
data class Task(val projectId: Int) :
    AbstractTask() {
  @PrimaryKey(autoGenerate = true)
  var id: Int = 0
  var priority: Priority = NONE
}