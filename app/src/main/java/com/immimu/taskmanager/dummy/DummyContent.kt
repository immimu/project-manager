package com.immimu.taskmanager.dummy

import com.immimu.taskmanager.dummy.Priority.LOW
import com.immimu.taskmanager.dummy.Priority.NONE
import com.immimu.taskmanager.dummy.Priority.URGENT
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
    ITEM_MAP.put(item.taskId, item)
  }

  private fun addProjectItem(id: Int) {
    projectItems.add(Project(id,"Project Name : ".plus(id), Date(), Date(),Status.TODO))
  }

  private fun createDummyItem(position: Int): Task {
    return Task().apply {
      taskId = position
      name = "Item ".plus(position)
      addSubTask(makeDetails(position))
    }
  }

  private fun makeDetails(position: Int): SubTask {
    val subTask = SubTask(position).apply {
      taskId = position
      name = "Details about Item: ".plus(position)
      status = Status.TODO
      priority = URGENT
    }
    return subTask
  }

  /**
   * A dummy item representing a piece of content.
   */
  open class Task {
    var taskId: Int = 0
    var name: String = ""
    var status: Status = Status.TODO
    var priority: Priority = NONE
    private val subTasks: MutableList<SubTask> = ArrayList()

    fun addSubTask(subTask: SubTask) {
      subTasks.add(subTask)
    }
  }

  data class SubTask(val id: Int) : Task()
}
