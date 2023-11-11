package com.haenari.haenari.presentation.base.activity

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.lifecycleScope
import com.haenari.haenari.presentation.base.viewmodel.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

abstract class MVIActivity<Binding : ViewDataBinding, Event, State>(@LayoutRes private val layoutId: Int) :
    BindActivity<Binding>(layoutId) {

    abstract val viewModel: BaseViewModel<Event, State>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
    }

    private fun initViewModel() {
        bind {
            lifecycleScope.launch(Dispatchers.Main) {
                viewModel.state.collect { state ->
                    render(state)
                }
            }
        }
    }

    protected abstract fun render(state: State)
}