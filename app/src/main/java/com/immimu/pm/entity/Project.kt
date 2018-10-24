package com.immimu.pm.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.Date

@Entity
open class Project {
  @PrimaryKey(autoGenerate = true)
  var id: Int = 0
  var name: String = ""
  var description: String = ""
  var createdAt: Date = Date()
}

enum class Status(val code: Int) {
  TODO(0), IN_PROGRESS(1), DONE(2)
}

enum class Priority(val code: Int) {
  URGENT(4), HIGH(3), MEDIUM(2), LOW(1), NONE(0)
}

enum class TimeUnit(val code: Int) {
  HOUR(0), DAYS(1), WEEK(2), MONTH(3)
}