package com.immimu.taskmanager.db.converter

import android.arch.persistence.room.TypeConverter
import java.util.Date

/**
 * Created by root on 01/10/17.
 */

class DateConverter {
  @TypeConverter
  fun toDate(timestamp: Long?): Date? {
    return if (timestamp == null) null else Date(timestamp)
  }

  @TypeConverter
  fun toTimestamp(date: Date?): Long? {
    return date?.time
  }
}
