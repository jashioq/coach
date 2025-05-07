package presentation.screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.displayCutoutPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

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
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            modifier = Modifier,
            text = "Hello $userName",
            fontSize = 32.sp,
            lineHeight = 48.sp,
        )
        Text(
            modifier = Modifier,
            text = "GoalId: $goalId",
            fontSize = 32.sp,
            lineHeight = 48.sp,
        )
        Text(
            modifier = Modifier,
            text = "GoalName: $goalName",
            fontSize = 32.sp,
            lineHeight = 48.sp,
        )
        Text(
            modifier = Modifier,
            text = "GoalFrequency: $goalFrequency",
            fontSize = 32.sp,
            lineHeight = 48.sp,
        )
    }
}
