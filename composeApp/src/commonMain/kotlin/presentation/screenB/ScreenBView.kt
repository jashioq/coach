package presentation.screenB

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.jh.coach.data.local.database.PostDto

@Composable
fun ScreenBView(
    modifier: Modifier = Modifier,
    onBackButtonClick: () -> Unit,
    count: Int,
    posts: List<PostDto>,
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text("Current count is $count")
        Button(onClick = onBackButtonClick) {
            Text("Go back")
        }
        Text("There are ${posts.size} posts")
    }
}
