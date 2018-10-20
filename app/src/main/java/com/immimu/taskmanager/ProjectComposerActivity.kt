package com.immimu.taskmanager

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import dagger.android.AndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_task_list.toolbar

class ProjectComposerActivity : AppCompatActivity(), HasSupportFragmentInjector {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_project_composer)
    setSupportActionBar(toolbar)

  }

  override fun supportFragmentInjector(): AndroidInjector<Fragment>? = null
}
