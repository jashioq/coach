package org.jh.project

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import navigation.RootComponent
import org.jetbrains.compose.ui.tooling.preview.Preview
import screens.ScreenA
import screens.ScreenB
import screens.onboarding.FirstSetupScreen
import screens.onboarding.OnboardingScreen

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
