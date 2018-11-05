package com.immimu.pm.db.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import android.arch.persistence.room.Transaction
import com.immimu.pm.entity.TaskWrapper

/**
 * Created by miftahmubarak on 9/22/17.
 */
@Dao
interface TaskWrapperDao {

  @Transaction
  @Query("SELECT * FROM task WHERE projectId=:projectId")
  fun allTask(projectId: Int): LiveData<List<TaskWrapper>>
}
