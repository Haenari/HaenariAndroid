package com.haenari.haenari.presentation.base.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.haenari.haenari.presentation.util.extension.channelToStateFlow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.StateFlow

abstract class BaseViewModel<Event, State>: ViewModel() {
    protected abstract var currentEvent: Event
    protected val events = Channel<Event>()
    abstract val state: StateFlow<State>

    fun initState(initialState: State): StateFlow<State> {
        return events.channelToStateFlow(initialState, ::changeState, viewModelScope)
    }

    protected suspend fun onEvent(event: Event) {
        currentEvent = event
        events.send(event)
    }

    protected abstract fun changeState(current: State, event: Event): State
}