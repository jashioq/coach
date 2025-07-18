package presentation.screen.onboarding.goalNameScreen

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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
import presentation.compose.component.progress.ProgressIndicatorState
import presentation.compose.component.progress.SharedProgressIndicator
import presentation.compose.component.text.AnimatedChangeText
import presentation.compose.component.text.Text
import presentation.compose.component.textField.PillTextField

@Composable
fun GoalNameScreenView(
    modifier: Modifier = Modifier,
    textFieldValue: String,
    textFieldPlaceholderIndex: Int,
    onTextFieldValueChange: (String) -> Unit,
    onInputDone: () -> Unit,
    userName: String,
    progressIndicatorState: ProgressIndicatorState,
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

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(32.dp)
            .displayCutoutPadding()
            .navigationBarsPadding(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.TopEnd,
            ) {
                SharedProgressIndicator(
                    modifier = Modifier.padding(16.dp),
                    progressIndicatorState = progressIndicatorState,
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = stringResource(Res.string.goal_name_screen_title, userName),
                fontSize = 32.sp,
                lineHeight = 48.sp,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = stringResource(Res.string.goal_name_screen_description),
            )
        }

        PillTextField(
            modifier = Modifier.imePadding(),
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
