package presentation.compose.component.preview

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coach.composeapp.generated.resources.Res
import coach.composeapp.generated.resources.dot_fill
import coach.composeapp.generated.resources.dot_hollow
import coach.composeapp.generated.resources.dot_hollow_crossed
import presentation.compose.component.text.Text
import org.jetbrains.compose.resources.painterResource
import presentation.compose.component.interaction.SwipeInteraction
import util.Logger

@Composable
fun GoalPreview() {
    val currentDay = 5
    val daysDone = listOf(1, 2, 4)

    Box(
        modifier = Modifier
            .padding(16.dp)
            .border(3.dp, Color.Black, RoundedCornerShape(16.dp))
    ) {
        SwipeInteraction(
            onDragDone = {
                Logger().d("dupa","onDragDone")
            },
            onActiveLongClick = {
                Logger().d("dupa","onActiveLongClick")
            },
            onActiveDoubleClick = {
                Logger().d("dupa","onActiveDoubleClick")
            },
            onDoneLongClick = {
                Logger().d("dupa","onDoneLongClick")
            },
            content = {
                Column(
                    verticalArrangement = Arrangement.Center,
                ) {
                    Text(
                        text = "Goal name",
                        modifier = Modifier
                            .padding(16.dp)
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        (1..7).map {
                            val done = daysDone.contains(it)
                            val upcoming = it > currentDay
                            val isToday = it == currentDay

                            DotIcon(done, upcoming, isToday)
                        }
                    }
                }
            }
        )
    }
}

@Composable
private fun DotIcon(
    done: Boolean,
    upcoming: Boolean,
    isToday: Boolean,
) {
    val painter = if (done)
        painterResource(Res.drawable.dot_fill)
    else if (upcoming || isToday)
        painterResource(Res.drawable.dot_hollow)
    else
        painterResource(Res.drawable.dot_hollow_crossed)

    val tint = if (upcoming)
        Color.LightGray
    else
        Color.Black

    Icon(
        modifier = Modifier.size(48.dp),
        painter = painter,
        tint = tint,
        contentDescription = "Icon",
    )
}