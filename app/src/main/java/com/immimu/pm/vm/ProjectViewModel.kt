package com.immimu.pm.vm

import android.arch.lifecycle.ViewModel
import com.hadisatrio.libs.android.viewmodelprovider.GeneratedProvider
import com.immimu.pm.db.dao.ProjectDao
import com.immimu.pm.db.dao.TaskDao
import com.immimu.pm.entity.Project
import com.immimu.pm.entity.Task
import javax.inject.Inject

@GeneratedProvider
class ProjectViewModel @Inject constructor(private val projectDao: ProjectDao,
    private val taskDao: TaskDao) :
    ViewModel() {

  val allProject = projectDao.allProject

  fun createProject(project: Project) {
    projectDao.insertProject(project)
  }

  fun createTask(task: Task) {
    taskDao.insertTask(task)
  }
}