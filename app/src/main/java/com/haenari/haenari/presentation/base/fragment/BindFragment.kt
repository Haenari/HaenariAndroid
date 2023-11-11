package com.haenari.haenari.presentation.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BindFragment<Binding : ViewDataBinding>(@LayoutRes private val layoutId: Int) :
    BaseFragment() {
    private var _binding: Binding? = null
    private val binding: Binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        savedInstanceState?.let { bundle ->
            initData(bundle)
        }
        initView()
    }

    protected fun bind(block: Binding.() -> Unit) {
        block(binding)
    }

    open fun initData(bundle: Bundle) {}
    abstract fun initView()
}