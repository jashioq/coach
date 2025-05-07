package presentation.screen.onboarding.goalNameScreen

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.LocalLifecycleOwner
import coach.composeapp.generated.resources.Res
import coach.composeapp.generated.resources.goal_name_placeholder_1
import coach.composeapp.generated.resources.goal_name_placeholder_10
import coach.composeapp.generated.resources.goal_name_placeholder_2
import coach.composeapp.generated.resources.goal_name_placeholder_3
import coach.composeapp.generated.resources.goal_name_placeholder_4
import coach.composeapp.generated.resources.goal_name_placeholder_5
import coach.composeapp.generated.resources.goal_name_placeholder_6
import coach.composeapp.generated.resources.goal_name_placeholder_7
import coach.composeapp.generated.resources.goal_name_placeholder_8
import coach.composeapp.generated.resources.goal_name_placeholder_9
import coach.composeapp.generated.resources.goal_name_screen_description
import coach.composeapp.generated.resources.goal_name_screen_title
import org.jetbrains.compose.resources.stringResource
import presentation.compose.component.progress.CircularProgressIndicator
import presentation.compose.component.text.AnimatedChangeText
import presentation.compose.component.textField.PillTextField

@Composable
fun GoalNameScreenView(
    modifier: Modifier = Modifier,
    textFieldValue: String,
    textFieldPlaceholderIndex: Int,
    onTextFieldValueChange: (String) -> Unit,
    onInputDone: () -> Unit,
    userName: String,
) {
    val placeholderList = listOf(
        stringResource(Res.string.goal_name_placeholder_1),
        stringResource(Res.string.goal_name_placeholder_2),
        stringResource(Res.string.goal_name_placeholder_3),
        stringResource(Res.string.goal_name_placeholder_4),
        stringResource(Res.string.goal_name_placeholder_5),
        stringResource(Res.string.goal_name_placeholder_6),
        stringResource(Res.string.goal_name_placeholder_7),
        stringResource(Res.string.goal_name_placeholder_8),
        stringResource(Res.string.goal_name_placeholder_9),
        stringResource(Res.string.goal_name_placeholder_10),
    )

    var progress by remember { mutableStateOf(0.25f) }
    val progressAnimDuration = 1_500

    val progressAnimation by animateFloatAsState(
        targetValue = progress,
        animationSpec = tween(durationMillis = progressAnimDuration, easing = FastOutSlowInEasing),
    )

    LaunchedEffect(LocalLifecycleOwner.current) {
        progress = 0.5f
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .displayCutoutPadding()
            .navigationBarsPadding(),
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
                text = stringResource(Res.string.goal_name_screen_title, userName),
                fontSize = 64.sp,
                lineHeight = 72.sp,
                fontWeight = FontWeight.Bold,
            )
            Text(
                modifier = Modifier,
                text = stringResource(Res.string.goal_name_screen_description),
                fontSize = 24.sp,
                lineHeight = 36.sp,
            )
        }

        PillTextField(
            modifier = Modifier
                .padding(32.dp)
                .imePadding(),
            value = textFieldValue,
            onValueChange = onTextFieldValueChange,
            displayDoneButton = textFieldValue.isNotEmpty(),
            onDoneClick = {
                if (textFieldValue.isNotEmpty()) onInputDone()
            },
            placeholder = {
                AnimatedChangeText(
                    modifier = Modifier,
                    text = placeholderList[textFieldPlaceholderIndex],
                    fontSize = 24.sp,
                    lineHeight = TextUnit.Unspecified,
                    fontWeight = FontWeight.Normal,
                )
            },
        )
    }
}
