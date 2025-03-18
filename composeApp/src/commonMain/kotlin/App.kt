import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext
import presentation.screen.onboarding.goalSelectionScreen.GoalSelectionScreen
import presentation.screen.onboarding.goalSelectionScreen.GoalSelectionScreenDestination
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
                        onNavigateToGoalSelectionScreen = {
                            navController.navigate(
                                GoalSelectionScreenDestination,
                            )
                        },
                    )
                }

                composable<GoalSelectionScreenDestination> {
                    GoalSelectionScreen()
                }
            }
        }
    }
}
