package presentation.screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.displayCutoutPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import domain.model.Goal
import domain.model.GoalState
import presentation.compose.component.button.PrimaryButton
import presentation.compose.component.preview.GoalPreview
import presentation.compose.component.text.Text

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenView(
    modifier: Modifier = Modifier,
    onGoalStateChange: (Long, GoalState) -> Unit,
    goals: List<Goal>,
) {
    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }

    var selectedGoal by remember { mutableStateOf<Goal?>(null) }

    if (showBottomSheet) {
        selectedGoal?.let { goal ->
            ModalBottomSheet(
                containerColor = MaterialTheme.colorScheme.surfaceContainer,
                onDismissRequest = {
                    showBottomSheet = false
                    selectedGoal = null
                },
                sheetState = sheetState,
            ) {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 32.dp),
                    horizontalAlignment = Alignment.Start,
                ) {
                    Text(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        text = "Goal state is: ${goal.state.name}",
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    if (goal.state == GoalState.DONE) {
                        PrimaryButton(
                            text = "SetGoal active",
                            onClick = {
                                onGoalStateChange(goal.id, GoalState.ACTIVE)
                                showBottomSheet = false
                                selectedGoal = null
                            },
                        )
                    }
                }
            }
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .displayCutoutPadding()
            .navigationBarsPadding(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        goals.map { goal ->
            GoalPreview(
                goalName = goal.name,
                goalState = goal.state,
                goalCompletions = goal.completions,
                onGoalStateChange = { onGoalStateChange(goal.id, it) },
                onOptionsOpen = {
                    selectedGoal = goal
                    showBottomSheet = true
                },
            )
        }
    }
}
