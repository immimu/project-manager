package com.immimu.pm.vm

import android.arch.lifecycle.ViewModel
import com.hadisatrio.libs.android.viewmodelprovider.GeneratedProvider
import com.immimu.pm.db.dao.ProjectDao
import com.immimu.pm.db.dao.ProjectWrapperDao
import com.immimu.pm.db.dao.SubTaskDao
import com.immimu.pm.db.dao.TaskDao
import com.immimu.pm.db.dao.TaskWrapperDao
import com.immimu.pm.entity.Project
import com.immimu.pm.entity.SubTask
import com.immimu.pm.entity.Task
import javax.inject.Inject

@GeneratedProvider
class ProjectViewModel @Inject constructor(private val projectDao: ProjectDao,
    private val projectWrapperDao: ProjectWrapperDao,
    private val taskDao: TaskDao, private val taskWrapperDao: TaskWrapperDao,
    private val subTaskDao: SubTaskDao) :
    ViewModel() {

  val allProject = projectWrapperDao.allProject()

  fun getAllTask(projectId: Int) = taskWrapperDao.allTask(projectId)

  fun getAllSubTask(parentTaskId: Int) = subTaskDao.allTask(parentTaskId)

  fun createProject(project: Project) {
    projectDao.insertProject(project)
  }

  fun createTask(task: Task) {
    taskDao.insertTask(task)
  }

  fun createSubTask(subTask: SubTask) {
    subTaskDao.insertTask(subTask)
  }

  fun deleteProject(project: Project) {
    projectDao.delete(project)
  }

  fun getProjectById(projectId: Int) = projectDao.getProjectById(projectId)

  fun getTaskById(taskId: Int) = taskDao.getTaskById(taskId)

  fun getSubTaskById(taskId: Int) = subTaskDao.getSubTaskById(taskId)

  fun deleteTask(task: Task) {
    taskDao.delete(task)
  }

  fun updateProject(project: Project) {
    projectDao.update(project)
  }

  fun updateTask(task: Task) {
    taskDao.update(task)
  }

  fun updateSubTask(task: SubTask) {
    subTaskDao.update(task)
  }
}