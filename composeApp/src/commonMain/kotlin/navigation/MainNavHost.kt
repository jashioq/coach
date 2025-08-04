package navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import presentation.compose.component.blob.BlobState
import presentation.screen.home.HomeScreen
import presentation.screen.home.HomeScreenDestination
import util.Logger

@Composable
fun MainNavHost() {
    val navController = rememberNavController()
    val blobState = remember { BlobState() }
    NavHost(
        navController = navController,
        startDestination = HomeScreenDestination,
    ) {
        composable<HomeScreenDestination> {
            HomeScreen(
                blobState = blobState,
                onSetBlobMode = { mode ->
                    blobState.updateMode(mode)
                }
            )
        }
    }
}
