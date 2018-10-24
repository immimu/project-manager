package com.immimu.pm.vm

import android.arch.lifecycle.ViewModel
import com.hadisatrio.libs.android.viewmodelprovider.GeneratedProvider
import com.immimu.pm.db.dao.ProjectDao
import com.immimu.pm.db.dao.SubTaskDao
import com.immimu.pm.db.dao.TaskDao
import com.immimu.pm.entity.Project
import com.immimu.pm.entity.Task
import javax.inject.Inject

@GeneratedProvider
class ProjectViewModel @Inject constructor(private val projectDao: ProjectDao,
    private val taskDao: TaskDao, private val subTaskDao: SubTaskDao) :
    ViewModel() {

  val allProject = projectDao.allProject

  fun getAllTask(projectId: Int) = taskDao.allTask(projectId)

  fun getAllSubTask(parentTaskId: Int) = subTaskDao.allTask(parentTaskId)

  fun createProject(project: Project) {
    projectDao.insertProject(project)
  }

  fun createTask(task: Task) {
    taskDao.insertTask(task)
  }

  fun deleteProject(project: Project) {
    projectDao.delete(project)
  }

}