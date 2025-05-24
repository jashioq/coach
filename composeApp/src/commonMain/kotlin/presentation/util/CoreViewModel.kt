package presentation.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import util.Logger

open class CoreViewModel<S, A>(
    initialState: S,
    scope: CoroutineScope?,
    logger: Logger?,
) : ViewModel() {
    @Suppress("PropertyName")
    protected val _state = MutableStateFlow(initialState)
    val state = _state.asStateFlow()

    val vmScope = scope ?: viewModelScope

    private val logger = logger ?: Logger()

    private val tag = this::class.simpleName.toString()

    protected fun A.process(
        processor: (A) -> Unit,
    ) {
        logger.d(tag, "Processing action: $this")
        processor.invoke(this)
    }

    fun foo() {}
}
