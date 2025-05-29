package presentation.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import util.Logger

/**
 * A generic view model that governs state management, coroutine scope injections and logging.
 * @param S the type of the state.
 * @param A the type of the action.
 * @param initialState the initial [S] state.
 * @param scope the [CoroutineScope] to be used for coroutines.
 * @param logger the [Logger] to be used for logging.
 */
open class CoreViewModel<S, A>(
    initialState: S,
    scope: CoroutineScope?,
    logger: Logger?,
) : ViewModel() {
    @Suppress("PropertyName")
    protected val _state = MutableStateFlow(initialState)
    internal val state = _state.asStateFlow()

    /**
     * The [CoroutineScope] to be used for coroutines.
     * [vmScope] should be used in view models instead of viewModelScope.
     */
    protected val vmScope = scope ?: viewModelScope

    /**
     * The [Logger] to be used for logging.
     * [vmLogger] should be used in view models instead of [Logger].
     */
    protected val vmLogger = logger ?: Logger()

    private val tag = this::class.simpleName.toString()

    /**
     * Logs and processes the given [A] action, using the given [processor] function.
     * @param processor the function to be used for processing [A] actions.
     */
    protected fun A.process(
        processor: (A) -> Unit,
    ) {
        vmLogger.d(tag, "Processing action: $this")
        processor.invoke(this)
    }
}
