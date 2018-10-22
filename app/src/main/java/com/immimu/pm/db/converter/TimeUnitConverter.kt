package com.immimu.pm.db.converter

import android.arch.persistence.room.TypeConverter
import com.immimu.pm.entity.TimeUnit

/**
 * Created by root on 01/10/17.
 */

class TimeUnitConverter {

  @TypeConverter
  fun toUnit(code: Int): TimeUnit {
    return when (code) {
      1 -> TimeUnit.DAYS
      2 -> TimeUnit.WEEK
      3 -> TimeUnit.MONTH
      else -> TimeUnit.HOUR
    }
  }

  @TypeConverter
  fun toInt(timeUnit: TimeUnit): Int {
    return when (timeUnit) {
      TimeUnit.DAYS -> 1
      TimeUnit.WEEK -> 2
      TimeUnit.MONTH -> 3
      else -> 0
    }
  }
}
