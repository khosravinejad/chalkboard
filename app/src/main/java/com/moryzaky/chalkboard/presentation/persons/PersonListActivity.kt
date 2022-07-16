package com.moryzaky.chalkboard.presentation.persons

import android.os.Bundle
import com.moryzaky.chalkboard.R
import com.moryzaky.chalkboard.databinding.ActivityPersonListBinding
import com.moryzaky.chalkboard.presentation.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PersonListActivity :
    BaseActivity<ActivityPersonListBinding, PersonListViewModel>(PersonListViewModel::class.java) {
    override fun initializeUI(savedInstanceState: Bundle?) {
        TODO("Not yet implemented")
    }

    override fun getLayoutId() = R.layout.activity_person_list
}