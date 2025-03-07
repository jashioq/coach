import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext
import org.koin.compose.currentKoinScope
import presentation.screenA.ScreenA
import presentation.screenA.ScreenADestination
import presentation.screenB.ScreenB
import presentation.screenB.ScreenBDestination

@Composable
@Preview
fun App() {
    MaterialTheme {
        KoinContext {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = ScreenADestination,
            ) {
                composable<ScreenADestination> {
                    ScreenA(
                        onNavigateToScreenB = { counter ->
                            navController.navigate(
                                ScreenBDestination(
                                    count = counter,
                                ),
                            )
                        },
                    )
                }

                composable<ScreenBDestination> {
                    val args = it.toRoute<ScreenBDestination>()
                    ScreenB(
                        onNavigateBack = {
                            navController.popBackStack()
                        },
                        count = args.count,
                    )
                }
            }
        }
    }
}

@Composable
inline fun <reified T : ViewModel> koinViewModel(): T {
    val scope = currentKoinScope()
    return viewModel {
        scope.get<T>()
    }
}
