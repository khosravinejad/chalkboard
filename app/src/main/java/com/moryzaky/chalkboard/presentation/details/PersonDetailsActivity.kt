package com.moryzaky.chalkboard.presentation.details

import android.os.Bundle
import com.moryzaky.chalkboard.R
import com.moryzaky.chalkboard.databinding.ActivityPersonDetailsBinding
import com.moryzaky.chalkboard.presentation.base.BaseActivity
import com.moryzaky.chalkboard.utils.Constants.EXTRA_ID
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Morteza Khosravinejad on 16/07/2022.
 */

@AndroidEntryPoint
class PersonDetailsActivity :
    BaseActivity<ActivityPersonDetailsBinding, PersonDetailsViewModel>(PersonDetailsViewModel::class.java) {
    override fun initializeUI(savedInstanceState: Bundle?) {
        val id = intent.getStringExtra(EXTRA_ID)
        if (!id.isNullOrBlank()) {
            observe()
            viewModel.loadDetails(id)
        } else {
            // show error
        }
    }

    private fun observe() {
        viewModel.person.observe(this) {
            binding.apply {
                detailsAvatar.text = it?.getInitialLetters()
                detailsFullName.text = it?.getFullName()
                detailsAge.text = String.format(getString(R.string.age), it?.age.toString())
                backButton.setOnClickListener {
                    onBackPressed()
                }
            }
        }
    }

    override fun getLayoutId() = R.layout.activity_person_details
}