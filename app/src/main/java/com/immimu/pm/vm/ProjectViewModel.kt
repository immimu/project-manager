package com.immimu.pm.vm

import android.arch.lifecycle.ViewModel
import com.hadisatrio.libs.android.viewmodelprovider.GeneratedProvider
import com.immimu.pm.db.dao.ProjectDao
import com.immimu.pm.entity.Project
import javax.inject.Inject

@GeneratedProvider
class ProjectViewModel @Inject constructor(private val projectDao: ProjectDao) :
    ViewModel() {

  val allProject = projectDao.allProject

  fun createProject(project: Project) {
    projectDao.insertProject(project)
  }
}