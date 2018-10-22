package com.immimu.pm.entity

import com.immimu.pm.entity.TimeUnit.HOUR
import java.util.Date

abstract class AbstractTask {
  var name: String = ""
  var description: String = ""
  var createdAt: Date = Date()
  var updateAt: Date = Date()
  var target: Int = 0
  var startDate: Date? = null
  var timeUnit: TimeUnit = HOUR
  var status: Status = Status.TODO
}