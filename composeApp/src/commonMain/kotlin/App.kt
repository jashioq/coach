import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext
import presentation.screen.onboarding.goalFrequencyScreen.GoalFrequencyScreen
import presentation.screen.onboarding.goalFrequencyScreen.GoalFrequencyScreenDestination
import presentation.screen.onboarding.goalNameScreen.GoalNameScreen
import presentation.screen.onboarding.goalNameScreen.GoalNameScreenDestination
import presentation.screen.onboarding.nameScreen.NameScreen
import presentation.screen.onboarding.nameScreen.NameScreenDestination
import presentation.screen.onboarding.startScreen.StartScreen
import presentation.screen.onboarding.startScreen.StartScreenDestination

@Composable
@Preview
fun App() {
    MaterialTheme {
        KoinContext {
            val navController = rememberNavController()
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
                    )
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
                    )
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
                    )
                }

                composable<GoalFrequencyScreenDestination> {
                    val args = it.toRoute<GoalFrequencyScreenDestination>()
                    GoalFrequencyScreen(
                        goalName = args.goalName,
                    )
                }
            }
        }
    }
}
