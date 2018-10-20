package com.immimu.taskmanager.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey
import com.immimu.taskmanager.entity.Priority.NONE

@Entity
@ForeignKey(entity = Project::class, parentColumns = ["id"],
    childColumns = ["parentTaskId"])
data class SubTask(val parentTaskId: Int) :
    AbstrackTask() {

  @PrimaryKey(autoGenerate = true)
  var id: Int = 0
  var priority: Priority = NONE
}