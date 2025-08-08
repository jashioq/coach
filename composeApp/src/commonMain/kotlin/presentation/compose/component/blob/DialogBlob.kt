package presentation.compose.component.blob

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun DialogBlob(
    modifier: Modifier = Modifier,
    height: Dp,
) {
    Surface(
        modifier
            .height(height)
            .fillMaxWidth(),
        color = Color.Blue,
        shape = RoundedCornerShape(32.dp)
    ){}
}