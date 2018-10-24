package com.immimu.pm.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey

@Entity(foreignKeys = [ForeignKey(entity = Project::class, parentColumns = ["id"],
    childColumns = ["projectId"], onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE)])
data class Task(val projectId: Int) :
    AbstractTask() {
  @PrimaryKey(autoGenerate = true)
  var id: Int = 0
}