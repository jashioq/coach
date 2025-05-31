package util


import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

actual class Haptic(private val context: Context) {

    private val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

    @Suppress("DEPRECATION")
    private fun vibrateOneShot(milliseconds: Long, amplitude: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(VibrationEffect.createOneShot(milliseconds, amplitude))
        } else {
            vibrator.vibrate(milliseconds)
        }
    }

    actual fun performLightImpact() {
        vibrateOneShot(50, 100)
    }

    actual fun performMediumImpact() {
        vibrateOneShot(50, 175)
    }

    actual fun performHeavyImpact() {
        vibrateOneShot(50, 255)
    }
}

@Composable
actual fun rememberHapticFeedback(): Haptic {
    val context = LocalContext.current
    return Haptic(context)
}