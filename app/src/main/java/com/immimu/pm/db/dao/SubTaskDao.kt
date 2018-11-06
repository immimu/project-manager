package com.immimu.pm.db.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update
import com.immimu.pm.entity.Status
import com.immimu.pm.entity.SubTask

/**
 * Created by miftahmubarak on 9/22/17.
 */
@Dao
interface SubTaskDao {

  @get:Query("SELECT * FROM subtask ORDER BY id DESC")
  val allTask: LiveData<List<SubTask>>

  @Query("SELECT * FROM subtask WHERE parentTaskId=:parentTaskId")
  fun allTask(parentTaskId: Int): LiveData<List<SubTask>>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertTask(task: SubTask)

  @Delete
  fun delete(task: SubTask)

  @Query("UPDATE subtask SET status=:status WHERE id=:taskId")
  fun updateStatus(taskId: Int,
      status: Status)

  @Query("SELECT * FROM subtask WHERE id=:taskId")
  fun getSubTaskById(taskId: Int): SubTask

  @Update
  fun update(task: SubTask)
}
