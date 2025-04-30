package presentation.screen.onboarding.startScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.displayCutoutPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coach.composeapp.generated.resources.Res
import coach.composeapp.generated.resources.placeholderImage
import org.jetbrains.compose.resources.painterResource
import presentation.compose.component.button.PrimaryButton
import presentation.compose.component.text.AnimatedChangeText

@Composable
fun StartScreenView(
    modifier: Modifier = Modifier,
    onPrimaryButtonClick: () -> Unit,
    text: String,
) {
    val primaryText = "Your Daily\nBoost for"
    val buttonText = "START"
    val painter = painterResource(Res.drawable.placeholderImage)

    Column(
        modifier = modifier
            .fillMaxSize()
            .displayCutoutPadding()
            .navigationBarsPadding(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            modifier = Modifier
                .weight(1f, fill = false)
                .aspectRatio(painter.intrinsicSize.width / painter.intrinsicSize.height)
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            painter = painter,
            contentDescription = null,
            contentScale = ContentScale.Fit,
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
        ) {
            Text(
                modifier = Modifier,
                text = primaryText,
                fontSize = 64.sp,
                lineHeight = 72.sp,
            )
            AnimatedChangeText(
                modifier = Modifier,
                text = text,
                fontSize = 64.sp,
                lineHeight = 36.sp,
                fontWeight = FontWeight.Bold,
            )
        }

        PrimaryButton(
            modifier = Modifier.padding(32.dp),
            text = buttonText,
            onClick = onPrimaryButtonClick,
        )
    }
}
