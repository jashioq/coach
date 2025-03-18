package presentation.compose.component.textField

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coach.composeapp.generated.resources.Res
import coach.composeapp.generated.resources.circle_arrow
import org.jetbrains.compose.resources.painterResource

@Composable
fun PillTextField(
    modifier: Modifier = Modifier,
    placeholder: String = "Aa",
    isEnabled: Boolean = true,
    onValueChange: (String) -> Unit,
    value: String,
    displayDoneButton: Boolean = true,
    onDoneClick: () -> Unit = {},
) {
    TextField(
        modifier = modifier
            .height(64.dp)
            .fillMaxWidth()
            .border(2.dp, Color.Black, RoundedCornerShape(50)),
        value = value,
        onValueChange = onValueChange,
        shape = RoundedCornerShape(50),
        enabled = isEnabled,
        placeholder = {
            Text(
                text = placeholder,
                fontSize = 24.sp,
            )
        },
        textStyle = TextStyle.Default.copy(
            fontSize = 24.sp,
        ),
        singleLine = true,
        keyboardActions = KeyboardActions {
            onDoneClick()
        },
        colors = TextFieldDefaults.colors(
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Black,
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedPlaceholderColor = Color.Gray,
            unfocusedPlaceholderColor = Color.Gray,
            cursorColor = Color.Black,
        ),
        trailingIcon = {
            if (displayDoneButton) {
                Icon(
                    painter = painterResource(resource = Res.drawable.circle_arrow),
                    contentDescription = "Done",
                    tint = Color.Black,
                    modifier = Modifier
                        .padding(16.dp)
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() },
                        ) {
                            onDoneClick()
                        },
                )
            }
        },
    )
}
