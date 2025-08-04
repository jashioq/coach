package presentation.compose.component.blob

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import util.Logger

@Composable
fun Blob(
    modifier: Modifier = Modifier,
    state: BlobState = remember { BlobState() },
    animationDuration: Int = 1000,
) {
    val transition = updateTransition(state, label = "blobTransition")

    var currentMode by remember { mutableStateOf(state.mode) }

    LaunchedEffect(transition.currentState, transition.isRunning) {
        if (!transition.isRunning || transition.currentState.mode == BlobMode.BUTTON_BAR) {
            currentMode = transition.currentState.mode
        }
    }

    val buttonBarCornerRadius by transition.animateDp(label = "buttonBarCornerRadius") { blobState ->
        when (blobState.mode) {
            BlobMode.NAVIGATION -> 0.dp
            else -> 32.dp
        }
    }

    val buttonBarHorizontalSpacing by transition.animateDp(
        label = "buttonBarHorizontalSpacing",
        transitionSpec = {
            if (this.initialState.mode == BlobMode.NAVIGATION) {
                spring(dampingRatio = Spring.DampingRatioNoBouncy)
            } else {
                spring(dampingRatio = Spring.DampingRatioHighBouncy)
            }
        }
    ) { blobState ->
        when (blobState.mode) {
            BlobMode.NAVIGATION -> 0.dp
            else -> 32.dp
        }
    }

    when (currentMode) {
        BlobMode.NAVIGATION -> NavigationBlob()
        else -> ButtonBarBlob(
            spacing = buttonBarHorizontalSpacing,
            innerCornerShape = buttonBarCornerRadius,
        )
    }
}