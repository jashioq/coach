package presentation.screen.onboarding.nameScreen

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.displayCutoutPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.LocalLifecycleOwner
import presentation.compose.component.progress.CircularProgressIndicator
import presentation.compose.component.textField.PillTextField

@Composable
fun NameScreenView(
    modifier: Modifier = Modifier,
    textFieldValue: String,
    onTextFieldValueChange: (String) -> Unit,
    onInputDone: () -> Unit,
) {
    val primaryText = "Name"
    val secondaryText = "Enter your name"

    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    var progress by remember { mutableStateOf(0f) }
    val progressAnimDuration = 1_500

    val progressAnimation by animateFloatAsState(
        targetValue = progress,
        animationSpec = tween(durationMillis = progressAnimDuration, easing = FastOutSlowInEasing),
    )

    LaunchedEffect(LocalLifecycleOwner.current) {
        progress = 0.25f
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .displayCutoutPadding()
            .navigationBarsPadding()
            .pointerInput(Unit) {
                detectTapGestures {
                    keyboardController?.hide()
                    focusManager.clearFocus()
                }
            },
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.TopEnd,
            ) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .padding(16.dp),
                    progress = progressAnimation,
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                modifier = Modifier,
                text = primaryText,
                fontSize = 64.sp,
                lineHeight = 72.sp,
                fontWeight = FontWeight.Bold,
            )
            Text(
                modifier = Modifier,
                text = secondaryText,
                fontSize = 24.sp,
                lineHeight = 36.sp,
            )
        }

        PillTextField(
            modifier = Modifier
                .padding(32.dp)
                .imePadding(),
            value = textFieldValue,
            capitalizeKeyboard = true,
            onValueChange = onTextFieldValueChange,
            displayDoneButton = textFieldValue.isNotEmpty(),
            onDoneClick = {
                if (textFieldValue.isNotEmpty()) onInputDone()
            },
            placeholder = {
                Text(
                    text = "Aa",
                    fontSize = 24.sp,
                )
            },
        )
    }
}
