package com.immimu.taskmanager.entity

import com.immimu.taskmanager.entity.TimeUnit.HOUR
import java.util.Date

abstract class AbstrackTask {
  var name: String = ""
  var description: String = ""
  var createdAt: Date? = null
  var updateAt: Date? = null
  var target: Int = 0
  var startDate: Date? = null
  var timeUnit: TimeUnit = HOUR
  var status: Status = Status.TODO
}