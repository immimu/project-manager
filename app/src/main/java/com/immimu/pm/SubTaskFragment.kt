package com.immimu.pm

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.immimu.pm.R.dimen
import com.immimu.pm.adapter.ProjectItemDecoration
import com.immimu.pm.adapter.SubTaskAdapter
import com.immimu.pm.context.EXTRA_PARENT_TASK_ID
import com.immimu.pm.di.Injectable
import com.immimu.pm.entity.Task
import com.immimu.pm.intent.IntentFactory
import com.immimu.pm.vm.ProjectViewModel
import kotlinx.android.synthetic.main.activity_task_list.toolbar
import kotlinx.android.synthetic.main.empty_view.emptyContainer
import kotlinx.android.synthetic.main.empty_view.emptyTextView
import kotlinx.android.synthetic.main.sub_task.fab
import kotlinx.android.synthetic.main.sub_task.subTaskList
import javax.inject.Inject

/**
 * A fragment representing a single Task detail screen.
 * This fragment is either contained in a [TaskListActivity]
 * in two-pane mode (on tablets) or a [SubTaskActivity]
 * on handsets.
 */
class SubTaskFragment : Fragment(), Injectable {

  /**
   * The dummy content this fragment is presenting.
   */

  @Inject
  lateinit var projectViewModel: ProjectViewModel
  @Inject
  lateinit var intentFactory: IntentFactory
  private val subTaskAdapter = SubTaskAdapter()

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.sub_task, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    emptyTextView.text = getString(R.string.text_empty_sub_task)
    emptyTextView.setOnClickListener { createSubTask() }

    setupRecyclerView(subTaskList)

    arguments?.let {
      if (it.containsKey(EXTRA_PARENT_TASK_ID)) {
        val parentTaskId = it.getInt(EXTRA_PARENT_TASK_ID)
        val task: Task? = projectViewModel.getTaskById(parentTaskId)
        task?.let { parentTask ->
          activity?.toolbar?.title = parentTask.name
        }
        projectViewModel.getAllSubTask(parentTaskId).observe(this, Observer { items ->
          if (items != null && items.isNotEmpty()) {
            emptyContainer.visibility = View.GONE
            subTaskAdapter.values.clear()
            subTaskAdapter.values.addAll(items)
            subTaskAdapter.notifyDataSetChanged()
          } else {
            emptyContainer.visibility = View.VISIBLE
            subTaskAdapter.values.clear()
            subTaskAdapter.notifyDataSetChanged()
          }
        })
      }
    }
    fab.setOnClickListener { createSubTask() }
  }

  private fun createSubTask() {
    arguments?.let {
      if (it.containsKey(EXTRA_PARENT_TASK_ID)) {
        val taskId = it.getInt(EXTRA_PARENT_TASK_ID)
        activity?.let {
          startActivity(intentFactory.createTaskComposerScreen(it, taskId, true))
        }
      }
    }
  }

  private fun setupRecyclerView(recyclerView: RecyclerView) {
    recyclerView.adapter = subTaskAdapter
    recyclerView.addItemDecoration(
        ProjectItemDecoration(resources.getDimensionPixelSize(dimen.margin_10dp)))
  }
}
