package com.immimu.pm.db.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import android.arch.persistence.room.Transaction
import com.immimu.pm.entity.ProjectWrapper

/**
 * Created by miftahmubarak on 9/22/17.
 */
@Dao
interface ProjectWrapperDao {

  @Transaction
  @Query("SELECT * FROM project ORDER BY id DESC")
  fun allProject(): LiveData<List<ProjectWrapper>>
}
