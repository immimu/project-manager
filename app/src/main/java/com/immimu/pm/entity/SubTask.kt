package com.immimu.pm.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey

@Entity(foreignKeys = [ForeignKey(entity = Task::class, parentColumns = ["id"],
    childColumns = ["parentTaskId"], onDelete = ForeignKey.CASCADE,
    onUpdate = ForeignKey.CASCADE)], indices = [(Index("parentTaskId"))])
data class SubTask(val parentTaskId: Int) :
    AbstractTask() {

  @PrimaryKey(autoGenerate = true)
  var id: Int = 0
}