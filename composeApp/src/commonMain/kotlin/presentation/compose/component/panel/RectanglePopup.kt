package presentation.compose.component.panel

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties

@Composable
fun rememberPopupState() = rememberSaveable { mutableStateOf(false) }

@Composable
fun RectanglePopup(
    visibleState: MutableState<Boolean>,
    onDismissRequest: () -> Unit = { visibleState.value = false },
    content: @Composable () -> Unit
) {
    if (visibleState.value) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.6f))
                .clickable { onDismissRequest() }
        ) {
            Popup(
                alignment = Alignment.Center,
                onDismissRequest = onDismissRequest,
                properties = PopupProperties(
                    dismissOnBackPress = true,
                    dismissOnClickOutside = true,
                    focusable = true
                )
            ) {
                Surface(
                    shape = MaterialTheme.shapes.medium,
                    modifier = Modifier
                        .wrapContentSize()
                ) {
                    Box(
                        modifier = Modifier
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        content()
                    }
                }
            }
        }
    }
}