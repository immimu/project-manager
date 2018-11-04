package com.immimu.pm

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.immimu.pm.R.dimen
import com.immimu.pm.adapter.AbstractTaskAdapter
import com.immimu.pm.adapter.ProjectItemDecoration
import com.immimu.pm.di.Injectable
import com.immimu.pm.entity.SubTask
import com.immimu.pm.entity.Task
import com.immimu.pm.vm.ProjectViewModel
import kotlinx.android.synthetic.main.activity_task_detail.toolbarLayout
import kotlinx.android.synthetic.main.task_detail.subTaskList
import javax.inject.Inject

/**
 * A fragment representing a single Task detail screen.
 * This fragment is either contained in a [TaskListActivity]
 * in two-pane mode (on tablets) or a [SubTaskActivity]
 * on handsets.
 */
class SubTasklFragment : Fragment(), Injectable {

  /**
   * The dummy content this fragment is presenting.
   */

  @Inject
  lateinit var projectViewModel: ProjectViewModel
  private val subTaskAdapter: AbstractTaskAdapter<SubTask> = AbstractTaskAdapter()

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.task_detail, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    setupRecyclerView(subTaskList)
    arguments?.let {
      if (it.containsKey(ARG_ITEM_ID)) {
        val task: Task? = projectViewModel.getTaskById(it.getInt(ARG_ITEM_ID))
        task?.let { parentTask ->
          activity?.toolbarLayout?.title = parentTask.name
        }
        projectViewModel.getAllSubTask(it.getInt(ARG_ITEM_ID)).observe(this, Observer { items ->
          if (items != null && items.isNotEmpty()) {
            subTaskAdapter.values.clear()
            subTaskAdapter.values.addAll(items)
            subTaskAdapter.notifyDataSetChanged()
          } else {
            subTaskAdapter.values.clear()
            subTaskAdapter.notifyDataSetChanged()
          }
        })
      }
    }
  }

  private fun setupRecyclerView(recyclerView: RecyclerView) {
    recyclerView.adapter = subTaskAdapter
    recyclerView.addItemDecoration(
        ProjectItemDecoration(resources.getDimensionPixelSize(dimen.margin_16dp)))
  }

  companion object {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    const val ARG_ITEM_ID = "item_id"
  }
}
