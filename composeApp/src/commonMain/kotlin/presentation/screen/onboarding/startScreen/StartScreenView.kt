package presentation.screen.onboarding.startScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.displayCutoutPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coach.composeapp.generated.resources.Res
import coach.composeapp.generated.resources.blob
import coach.composeapp.generated.resources.start_button
import coach.composeapp.generated.resources.start_screen_text_1
import coach.composeapp.generated.resources.start_screen_text_2
import coach.composeapp.generated.resources.start_screen_text_3
import coach.composeapp.generated.resources.start_screen_text_4
import coach.composeapp.generated.resources.start_screen_text_5
import coach.composeapp.generated.resources.start_screen_title
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import presentation.compose.component.button.PrimaryButton
import presentation.compose.component.text.AnimatedChangeText
import presentation.compose.component.text.Text

@Composable
fun StartScreenView(
    modifier: Modifier = Modifier,
    onPrimaryButtonClick: () -> Unit,
    textIndex: Int,
) {
    val painter = painterResource(Res.drawable.blob)
    val painterTint = if (isSystemInDarkTheme()) Color.White else Color.Black
    val textList = listOf(
        stringResource(Res.string.start_screen_text_1),
        stringResource(Res.string.start_screen_text_2),
        stringResource(Res.string.start_screen_text_3),
        stringResource(Res.string.start_screen_text_4),
        stringResource(Res.string.start_screen_text_5),
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(32.dp)
            .displayCutoutPadding()
            .navigationBarsPadding(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            modifier = Modifier
                .aspectRatio(painter.intrinsicSize.width / painter.intrinsicSize.height)
                .fillMaxWidth(),
            painter = painter,
            contentDescription = null,
            contentScale = ContentScale.Fit,
            colorFilter = ColorFilter.tint(painterTint),
        )
        Column(
            modifier = Modifier
                .padding(top = 32.dp)
                .fillMaxWidth()
                .weight(1f),
        ) {
            Text(
                text = stringResource(Res.string.start_screen_title),
                fontSize = 64.sp,
                lineHeight = 72.sp,
            )
            AnimatedChangeText(
                modifier = Modifier.padding(bottom = 16.dp),
                color = MaterialTheme.colorScheme.primary,
                text = textList[textIndex],
                fontSize = 64.sp,
                lineHeight = 72.sp,
            )
        }

        PrimaryButton(
            text = stringResource(Res.string.start_button),
            onClick = onPrimaryButtonClick,
        )
    }
}
