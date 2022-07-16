package com.moryzaky.chalkboard.presentation.persons

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.moryzaky.chalkboard.R
import com.moryzaky.chalkboard.databinding.ActivityPersonListBinding
import com.moryzaky.chalkboard.domain.model.PersonDomainModel
import com.moryzaky.chalkboard.presentation.base.BaseActivity
import com.moryzaky.chalkboard.presentation.base.OnItemClickListener
import com.moryzaky.chalkboard.presentation.persons.recyclerview.PersonsAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PersonListActivity :
    BaseActivity<ActivityPersonListBinding, PersonListViewModel>(PersonListViewModel::class.java),
    OnItemClickListener<PersonDomainModel> {

    @Inject
    lateinit var personsAdapter: PersonsAdapter

    private val layoutManager by lazy {
        LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    override fun initializeUI(savedInstanceState: Bundle?) {

        binding.recyclerview.apply {
            adapter = personsAdapter
            layoutManager = this@PersonListActivity.layoutManager
        }

        observe()
        viewModel.loadPersons()
        personsAdapter.setOnItemClickListener(this)
    }

    private fun observe() {
        viewModel.persons.observe(this) {
            personsAdapter.submitData(lifecycle, it)
        }
    }

    override fun getLayoutId() = R.layout.activity_person_list
    override fun onItemClick(item: PersonDomainModel) {

    }
}