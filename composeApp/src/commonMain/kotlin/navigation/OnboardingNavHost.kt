package navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import presentation.compose.component.progress.ProgressIndicatorState
import presentation.screen.onboarding.goalFrequencyScreen.GoalFrequencyScreen
import presentation.screen.onboarding.goalFrequencyScreen.GoalFrequencyScreenDestination
import presentation.screen.onboarding.goalNameScreen.GoalNameScreen
import presentation.screen.onboarding.goalNameScreen.GoalNameScreenDestination
import presentation.screen.onboarding.nameScreen.NameScreen
import presentation.screen.onboarding.nameScreen.NameScreenDestination
import presentation.screen.onboarding.startScreen.StartScreen
import presentation.screen.onboarding.startScreen.StartScreenDestination

@Composable
fun OnboardingNavHost() {
    val navController = rememberNavController()
    val progressIndicatorState = remember { ProgressIndicatorState() }
    NavHost(
        navController = navController,
        startDestination = StartScreenDestination,
    ) {
        composable<StartScreenDestination> {
            StartScreen(
                onNavigateToNameScreen = {
                    navController.navigate(
                        NameScreenDestination,
                    )
                },
            ).also {
                progressIndicatorState.updateProgress(0f)
            }
        }

        composable<NameScreenDestination> {
            NameScreen(
                onNavigateToGoalNameScreen = { userName ->
                    navController.navigate(
                        GoalNameScreenDestination(
                            userName = userName,
                        ),
                    )
                },
                progressIndicatorState = progressIndicatorState,
            ).also {
                progressIndicatorState.updateProgress(0.25f)
            }
        }

        composable<GoalNameScreenDestination> {
            val args = it.toRoute<GoalNameScreenDestination>()
            GoalNameScreen(
                onNavigateToGoalFrequencyScreen = { goalName ->
                    navController.navigate(
                        GoalFrequencyScreenDestination(
                            goalName = goalName,
                        ),
                    )
                },
                userName = args.userName,
                progressIndicatorState = progressIndicatorState,
            ).also {
                progressIndicatorState.updateProgress(0.5f)
            }
        }

        composable<GoalFrequencyScreenDestination> {
            val args = it.toRoute<GoalFrequencyScreenDestination>()
            GoalFrequencyScreen(
                goalName = args.goalName,
                progressIndicatorState = progressIndicatorState,
            ).also {
                progressIndicatorState.updateProgress(0.75f)
            }
        }
    }
}
