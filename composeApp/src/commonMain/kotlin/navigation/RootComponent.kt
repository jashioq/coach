package navigation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.pushNew
import com.arkivanov.decompose.router.stack.replaceAll
import kotlinx.serialization.Serializable
import navigation.rootStack.screenA.ScreenAComponent
import navigation.rootStack.screenB.ScreenBComponent
import navigation.onboardingStack.firstSetupScreen.FirstSetupScreenComponent
import navigation.onboardingStack.onboardingScreen.OnboardingScreenComponent

class RootComponent(
    componentContext: ComponentContext,
) : ComponentContext by componentContext {
    private val navigation = StackNavigation<Configuration>()

    val childStack = childStack(
        source = navigation,
        serializer = Configuration.serializer(),
        initialConfiguration = Configuration.ScreenA,
        handleBackButton = true,
        childFactory = ::createChild,
    )

    val onboardingChildStack = childStack(
        source = navigation,
        key = "OnboardingChildStack",
        serializer = Configuration.serializer(),
        initialConfiguration = Configuration.OnboardingScreen,
        handleBackButton = false,
        childFactory = ::createChild,
    )

    @OptIn(ExperimentalDecomposeApi::class)
    private fun createChild(
        config: Configuration,
        context: ComponentContext,
    ): Child {
        return when (config) {
            Configuration.OnboardingScreen -> Child.OnboardingScreen(
                OnboardingScreenComponent(
                    componentContext = context,
                    onNavigateForward = {
                        navigation.replaceAll(Configuration.FirstSetupScreen)
                    }
                )
            )

            Configuration.FirstSetupScreen -> Child.FirstSetupScreen(
                FirstSetupScreenComponent(
                    componentContext = context,
                    onComplete = {},
                )
            )

            Configuration.ScreenA -> Child.ScreenA(
                ScreenAComponent(
                    componentContext = context,
                    onNavigateToScreenB = { text ->
                        navigation.pushNew(Configuration.ScreenB(text))
                    },
                ),
            )

            is Configuration.ScreenB -> Child.ScreenB(
                ScreenBComponent(
                    componentContext = context,
                    text = config.text,
                    onBack = {
                        navigation.pop()
                    },
                ),
            )
        }
    }

    sealed class Child {
        data class OnboardingScreen(val component: OnboardingScreenComponent) : Child()
        data class FirstSetupScreen(val component: FirstSetupScreenComponent) : Child()
        data class ScreenA(val component: ScreenAComponent) : Child()
        data class ScreenB(val component: ScreenBComponent) : Child()
    }

    @Serializable
    sealed class Configuration {
        @Serializable
        data object OnboardingScreen : Configuration()

        @Serializable
        data object FirstSetupScreen : Configuration()

        @Serializable
        data object ScreenA : Configuration()

        @Serializable
        data class ScreenB(val text: String) : Configuration()
    }
}
