package presentation.util

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import util.Logger

open class CoreViewModel<S, A>(
    initialState: S,
) : ViewModel() {
    @Suppress("PropertyName")
    val _state = MutableStateFlow(initialState)
    val state = _state.asStateFlow()

    private val tag = this::class.simpleName.toString()

    protected open fun A.process(
        processor: (A) -> Unit,
    ) {
        Logger.d(tag, "Processing action: $this")
        processor.invoke(this)
    }
}
