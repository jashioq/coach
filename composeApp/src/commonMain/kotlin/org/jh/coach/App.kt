package org.jh.coach

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import org.jetbrains.compose.ui.tooling.preview.Preview
import presentation.RootComponent
import presentation.firstSetupScreen.FirstSetupScreen
import presentation.onboardingScreen.OnboardingScreen
import presentation.screenA.ScreenA
import presentation.screenB.ScreenB

@Composable
@Preview
fun App(root: RootComponent) {
    MaterialTheme {
        val rootChildStack by root.childStack.subscribeAsState()
        val onboardingChildStack by root.onboardingChildStack.subscribeAsState()

        val stack = if (false) rootChildStack else onboardingChildStack

        Children(
            stack = stack,
            animation = stackAnimation(slide()),
        ) { child ->
            when (val instance = child.instance) {
                is RootComponent.Child.OnboardingScreen -> OnboardingScreen(component = instance.component)
                is RootComponent.Child.FirstSetupScreen -> FirstSetupScreen(component = instance.component)
                is RootComponent.Child.ScreenA -> ScreenA(component = instance.component)
                is RootComponent.Child.ScreenB -> ScreenB(
                    component = instance.component,
                    text = instance.component.text,
                )
            }
        }
    }
}
