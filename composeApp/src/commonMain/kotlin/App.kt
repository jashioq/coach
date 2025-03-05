
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext
import org.koin.compose.currentKoinScope
import presentation.screenA.ScreenA
import presentation.screenA.ScreenADestination

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
                        onNavigateToScreenB = { name, age ->
                            navController.navigate(
                                ScreenB(
                                    name = name,
                                    age = age,
                                ),
                            )
                        },
                    )
                }
                composable<ScreenB> {
                    val args = it.toRoute<ScreenB>()
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text("${args.name}, ${args.age} years old")
                        Button(onClick = {
                            navController.popBackStack()
                        }) {
                            Text("Go back")
                        }
                    }
                }
            }
        }
    }
}

@Serializable
data class ScreenB(
    val name: String?,
    val age: Int,
)

@Composable
inline fun <reified T : ViewModel> koinViewModel(): T {
    val scope = currentKoinScope()
    return viewModel {
        scope.get<T>()
    }
}
