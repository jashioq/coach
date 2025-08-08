package presentation.compose.component.blob

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun NavigationBlob(
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier
            .height(64.dp)
            .fillMaxWidth(),
        color = Color.Blue,
        shape = RoundedCornerShape(32.dp)
    ){}
}