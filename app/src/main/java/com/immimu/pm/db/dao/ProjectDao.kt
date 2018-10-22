package com.immimu.pm.db.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.immimu.pm.entity.Project
import com.immimu.pm.entity.Status

/**
 * Created by miftahmubarak on 9/22/17.
 */
@Dao
interface ProjectDao {

  @get:Query("SELECT * FROM project ORDER BY id DESC")
  val allProject: LiveData<List<Project>>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertProject(project: Project)

  @Delete
  fun delete(project: Project)

  @Query("SELECT * FROM project WHERE id=:projectId")
  fun getProjectById(projectId: Int): Project

  @Query("UPDATE project SET status=:status WHERE id=:projectId")
  fun updateStatus(projectId: Int,
      status: Status)

  @Query("DELETE FROM project WHERE id=:projectId")
  fun deleteById(projectId: Int)
}
