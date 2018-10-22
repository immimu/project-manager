package com.immimu.pm.entity

import com.immimu.pm.entity.Priority.URGENT
import java.util.ArrayList
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


  /**
   * A map of sample (dummy) items, by ID.
   */
  val ITEM_MAP: MutableMap<Int, Task> = HashMap()

  private val COUNT = 25

  init {
    // Add some sample items.
    for (i in 1..COUNT) {
      addItem(createDummyItem(i))
    }
  }

  private fun addItem(item: Task) {
    ITEMS.add(item)
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
