package com.haenari.haenari.presentation.views.sub

import androidx.activity.viewModels
import com.haenari.haenari.R
import com.haenari.haenari.databinding.ActivitySubBinding
import com.haenari.haenari.presentation.base.activity.MVIActivity

class SubActivity : MVIActivity<ActivitySubBinding, SubEvent, SubState>(R.layout.activity_sub) {
    override val viewModel: SubViewModel by viewModels()

    override fun render(state: SubState) {
        TODO("Not yet implemented")
    }

    override fun initView() {
        TODO("Not yet implemented")
    }
}