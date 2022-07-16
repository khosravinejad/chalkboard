package com.moryzaky.chalkboard.presentation.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.moryzaky.chalkboard.utils.FailureMessages

/**
 * Created by Morteza Khosravinejad on 16/07/2022.
 */

abstract class BaseActivity<T : ViewDataBinding, VM : BaseViewModel>(private val viewModelClass: Class<VM>) :
    AppCompatActivity() {

    protected val binding: T by binding()

    val viewModel: VM by lazy {
        ViewModelProvider(this)[viewModelClass]
    }

    protected abstract fun initializeUI(savedInstanceState: Bundle?)

    @LayoutRes
    abstract fun getLayoutId(): Int

    private fun binding(): Lazy<T> = lazy { DataBindingUtil.setContentView(this, getLayoutId()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding
        initializeUI(savedInstanceState)
        observeErrors()
    }

    private fun observeErrors() {
        viewModel.failure.observe(this) {
            Snackbar.make(
                binding.root,
                FailureMessages.getProperFailureMessage(this, it),
                Snackbar.LENGTH_LONG
            ).show()
        }
    }
}