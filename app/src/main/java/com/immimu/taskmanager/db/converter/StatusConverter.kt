package com.immimu.taskmanager.db.converter

import android.arch.persistence.room.TypeConverter
import com.immimu.taskmanager.entity.Status

/**
 * Created by root on 01/10/17.
 */

class StatusConverter {
  @TypeConverter
  fun toStatus(code: Int): Status {
    return when (code) {
      1 -> Status.IN_PROGRESS
      2 -> Status.DONE
      else -> Status.TODO
    }
  }

  @TypeConverter
  fun toInt(status: Status): Int {
    return when (status) {
      Status.IN_PROGRESS -> 1
      Status.DONE -> 2
      else -> 0
    }
  }
}
