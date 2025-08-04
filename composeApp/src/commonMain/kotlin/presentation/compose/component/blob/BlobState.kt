package presentation.compose.component.blob

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class BlobState(
    initialMode: BlobMode = BlobMode.NAVIGATION,
) {
    var mode by mutableStateOf(initialMode)
        private set

    fun updateMode(newMode: BlobMode) {
        mode = newMode
    }
}

enum class BlobMode{
    NAVIGATION,
    BUTTON_BAR,
    DIALOG,
    DIALOG_WITH_BUTTON_BAR,
}
