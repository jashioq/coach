package presentation.screenB

import androidx.compose.runtime.Composable

@Composable
fun ScreenB(
    onNavigateBack: () -> Unit,
    count: Int,
) {
    ScreenBView(
        onBackButtonClick = onNavigateBack,
        count = count,
    )
}
