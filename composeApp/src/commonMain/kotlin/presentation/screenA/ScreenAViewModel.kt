package presentation.screenA

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ScreenAViewModel(
    private val helloWorld: String,
) : ViewModel() {
    private val _counter = MutableStateFlow(0)
    val counter = _counter.asStateFlow()

    init {
        println(helloWorld)
    }

    fun action(action: ScreenAAction) {
        when (action) {
            ScreenAAction.IncrementCounter -> {
                incrementCounter()
            }
            is ScreenAAction.SetCounter -> {
                setCounter(action.newValue)
            }
            else -> { /*Do nothing*/ }
        }
    }

    private fun incrementCounter() = _counter.value++

    private fun setCounter(value: Int) {
        _counter.value = value
    }
}
