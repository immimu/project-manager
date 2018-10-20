package com.immimu.taskmanager.db.converter

import android.arch.persistence.room.TypeConverter
import com.immimu.taskmanager.entity.Priority

/**
 * Created by root on 01/10/17.
 */

class PriorityConverter {

  @TypeConverter
  fun toPriority(code: Int): Priority {
    return when (code) {
      1 -> Priority.LOW
      2 -> Priority.MEDIUM
      3 -> Priority.HIGH
      4 -> Priority.URGENT
      else -> Priority.NONE
    }
  }

  @TypeConverter
  fun toInt(priority: Priority): Int {
    return when (priority) {
      Priority.LOW -> 1
      Priority.MEDIUM -> 2
      Priority.HIGH -> 3
      Priority.URGENT -> 4
      else -> 0
    }
  }
}
