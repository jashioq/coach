package presentation.screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.displayCutoutPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.unit.sp
import presentation.compose.component.preview.GoalPreview
import util.Logger
import util.rememberHapticFeedback

@Composable
fun HomeScreenView(
    modifier: Modifier = Modifier,
    userName: String,
    goalId: String,
    goalName: String,
    goalFrequency: String,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .displayCutoutPadding()
            .navigationBarsPadding(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        GoalPreview()
    }
}
