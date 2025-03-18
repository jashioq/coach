package presentation.compose.component.progress

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CircularProgressIndicator(
    modifier: Modifier = Modifier,
    progress: Float = 0f,
) {
    androidx.compose.material3.CircularProgressIndicator(
        progress = { progress },
        modifier = modifier
            .size(32.dp),
        trackColor = Color.Transparent,
        color = Color.Black,
        strokeWidth = 6.dp,
    )
}
