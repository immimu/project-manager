package com.immimu.taskmanager.dummy

import java.util.Date

enum class Status {
  TODO, IN_PROGRESS, DONE
}

enum class Priority {
  URGENT, HIGHT, MEDIUM, LOW, NONE
}

data class Project(val id: Int, val name: String, val createdAt: Date, val updateAt: Date,
    val status: Status)