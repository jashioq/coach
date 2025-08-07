package presentation.compose.component.blob

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun ButtonBarBlob(
    modifier: Modifier = Modifier,
    spacing: Dp,
    innerCornerShape: Dp
) {

    Row(
        modifier.fillMaxWidth()
    ){
        Surface(
            modifier
                .height(64.dp)
                .weight(1f),
            color = Color.Blue,
            shape = RoundedCornerShape(32.dp, innerCornerShape, innerCornerShape, 32.dp),
        ){}

        Spacer(
            modifier.width(spacing)
        )

        Surface(
            modifier
                .height(64.dp)
                .weight(1f),
            color = Color.Blue,
            shape = RoundedCornerShape(innerCornerShape, 32.dp, 32.dp, innerCornerShape),
        ){}
    }
}