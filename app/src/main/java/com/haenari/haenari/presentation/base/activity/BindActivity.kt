package com.haenari.haenari.presentation.base.activity

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BindActivity<Binding : ViewDataBinding>(@LayoutRes private val layoutId: Int) :
    BaseActivity() {
    private var _binding: Binding? = null
    private val binding: Binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(this, layoutId)
        initData(savedInstanceState)
        initView()
    }

    protected fun bind(block: Binding.() -> Unit) {
        block(binding)
    }

    open fun initData(bundle: Bundle?) {}
    abstract fun initView()
}