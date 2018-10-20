package com.immimu.taskmanager.vm

import android.arch.lifecycle.ViewModel
import com.hadisatrio.libs.android.viewmodelprovider.GeneratedProvider
import com.immimu.taskmanager.db.dao.ProjectDao
import javax.inject.Inject

@GeneratedProvider
class ProjectViewModel @Inject constructor(private val projectDao: ProjectDao) :
    ViewModel() {

}