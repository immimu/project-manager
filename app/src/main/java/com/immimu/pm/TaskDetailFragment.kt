package com.immimu.pm

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.immimu.pm.entity.DummyContent
import com.immimu.pm.entity.Task
import kotlinx.android.synthetic.main.activity_task_detail.toolbar_layout
import kotlinx.android.synthetic.main.task_detail.view.task_detail

/**
 * A fragment representing a single Task detail screen.
 * This fragment is either contained in a [TaskListActivity]
 * in two-pane mode (on tablets) or a [TaskDetailActivity]
 * on handsets.
 */
class TaskDetailFragment : Fragment() {

  /**
   * The dummy content this fragment is presenting.
   */
  private var item: Task? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    arguments?.let {
      if (it.containsKey(ARG_ITEM_ID)) {
        // Load the dummy content specified by the fragment
        // arguments. In a real-world scenario, use a Loader
        // to load content from a content provider.
        item = DummyContent.ITEM_MAP[it.getInt(ARG_ITEM_ID)]
        activity?.toolbar_layout?.title = item?.name
      }
    }
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?): View? {
    val rootView = inflater.inflate(R.layout.task_detail, container, false)

    // Show the dummy content as text in a TextView.
    item?.let {
      rootView.task_detail.text = it.name
    }

    return rootView
  }

  companion object {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    const val ARG_ITEM_ID = "item_id"
  }
}
