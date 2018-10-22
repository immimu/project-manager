package com.immimu.pm.db.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.immimu.pm.entity.Status
import com.immimu.pm.entity.Task

/**
 * Created by miftahmubarak on 9/22/17.
 */
@Dao
interface TaskDao {

  @get:Query("SELECT * FROM task ORDER BY projectId DESC")
  val allTask: LiveData<List<Task>>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertTask(task: Task)

  @Delete
  fun delete(task: Task)

  @Query("SELECT * FROM task WHERE projectId=:taskId")
  fun getTaskById(taskId: Int): Task

  @Query("UPDATE task SET status=:status WHERE projectId=:taskId")
  fun updateStatus(taskId: Int,
      status: Status)

  @Query("DELETE FROM task WHERE projectId=:taskId")
  fun deleteById(taskId: Int)
}
