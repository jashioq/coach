package presentation.compose.component.blob

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Blob(
    modifier: Modifier = Modifier,
    state: BlobState = remember { BlobState() },
    animationController: BlobAnimationController,
) {
    LaunchedEffect(state.mode) {
        animationController.requestMode(state.mode)
    }

    val totalHeight = animationController.blobHeight.value

    val topHorizontalSpacing = animationController.topHorizontalSpacing.value
    val bottomHorizontalSpacing = animationController.bottomHorizontalSpacing.value

    val verticalSpacing = animationController.verticalSpacing.value

    val horizontalCornerRadius = animationController.horizontalCornerRadius.value
    val verticalCornerRadius = animationController.verticalCornerRadius.value

    val adjustedVerticalCornerRadius =
        if (verticalSpacing == 0f)
            32f
        else
            verticalCornerRadius

    val bottomHeight = 64f
    val dialogHeight =
        if (verticalSpacing == 0f)
            totalHeight
        else
            totalHeight - bottomHeight - verticalSpacing

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(Color.LightGray),
    ) {
        // Top part
        if (topHorizontalSpacing == 0f) {
            Surface(
                modifier
                    .height(dialogHeight.dp)
                    .fillMaxWidth(),
                color = Color.Blue,
                shape = RoundedCornerShape(32.dp, 32.dp, adjustedVerticalCornerRadius.dp, adjustedVerticalCornerRadius.dp)
            ){}
        } else {
            Row(
                modifier.fillMaxWidth()
            ){
                Surface(
                    modifier
                        .height(64.dp)
                        .weight(1f),
                    color = Color.Blue,
                    shape = RoundedCornerShape(32.dp, horizontalCornerRadius.dp, horizontalCornerRadius.dp, 32.dp),
                ){}

                Spacer(
                    modifier.width(topHorizontalSpacing.dp)
                )

                Surface(
                    modifier
                        .height(64.dp)
                        .weight(1f),
                    color = Color.Blue,
                    shape = RoundedCornerShape(horizontalCornerRadius.dp, 32.dp, 32.dp, horizontalCornerRadius.dp),
                ){}
            }
        }

        // Bottom part - dialog with buttons only
        if (verticalSpacing > 0f) {
            Spacer(modifier.height(verticalSpacing.dp))
            if (bottomHorizontalSpacing == 0f) {
                Surface(
                    modifier
                        .height(bottomHeight.dp)
                        .fillMaxWidth(),
                    color = Color.Blue,
                    shape = RoundedCornerShape(verticalCornerRadius.dp, verticalCornerRadius.dp,32.dp, 32.dp)
                ){}
            } else {
                Row(
                    modifier.fillMaxWidth()
                ){
                    Surface(
                        modifier
                            .height(64.dp)
                            .weight(1f),
                        color = Color.Blue,
                        shape = RoundedCornerShape(adjustedVerticalCornerRadius.dp, horizontalCornerRadius.dp, horizontalCornerRadius.dp, 32.dp),
                    ){}

                    Spacer(
                        modifier.width(bottomHorizontalSpacing.dp)
                    )

                    Surface(
                        modifier
                            .height(64.dp)
                            .weight(1f),
                        color = Color.Blue,
                        shape = RoundedCornerShape(horizontalCornerRadius.dp, adjustedVerticalCornerRadius.dp, 32.dp, horizontalCornerRadius.dp),
                    ){}
                }
            }
        }
    }
}