package presentation.compose.component.button

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PrimaryButton(
    modifier: Modifier = Modifier,
    text: String = "START",
    onClick: () -> Unit = {},
    isEnabled: Boolean = true,
) {
    Button(
        modifier = modifier
            .height(64.dp)
            .fillMaxWidth(),
        onClick = onClick,
        enabled = isEnabled,
        shape = RoundedCornerShape(50),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Black,
            contentColor = Color.White,
            disabledContentColor = Color.White,
        ),
    ) {
        Text(
            fontSize = 24.sp,
            text = text,
            fontWeight = FontWeight.Bold,
        )
    }
}
