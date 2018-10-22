package com.immimu.pm.entity

import com.immimu.pm.entity.Priority.URGENT
import com.immimu.pm.entity.TimeUnit.MONTH
import java.util.ArrayList
import java.util.Date
import java.util.HashMap

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 *
 * TODO: Replace all uses of this class before publishing your app.
 */
object DummyContent {

  /**
   * An array of sample (dummy) items.
   */
  val ITEMS: MutableList<Task> = ArrayList()

  val projectItems: MutableList<Project> = ArrayList()

  /**
   * A map of sample (dummy) items, by ID.
   */
  val ITEM_MAP: MutableMap<Int, Task> = HashMap()

  private val COUNT = 25

  init {
    // Add some sample items.
    for (i in 1..COUNT) {
      addItem(createDummyItem(i))
      addProjectItem(i)
    }
  }

  private fun addItem(item: Task) {
    ITEMS.add(item)
  }

  private fun addProjectItem(id: Int) {
    val project = Project().apply {
      name = "Project ".plus(id)
      description = "Description ".plus(id)
      createdAt = Date()
      updateAt = Date()
      target = 2
      timeUnit = MONTH
    }
    projectItems.add(project)
  }

  private fun createDummyItem(position: Int): Task {
    return Task(position).apply {
      name = "Item ".plus(position)
    }
  }

  private fun makeDetails(position: Int): SubTask {
    val subTask = SubTask(position).apply {
      name = "Details about Item: ".plus(position)
      status = Status.TODO
      priority = URGENT
    }
    return subTask
  }
}
