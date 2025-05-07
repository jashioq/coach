package di

import org.koin.dsl.module
import presentation.screen.home.HomeScreenViewModel
import presentation.screen.onboarding.goalFrequencyScreen.GoalFrequencyScreenViewModel
import presentation.screen.onboarding.goalNameScreen.GoalNameScreenViewModel
import presentation.screen.onboarding.nameScreen.NameScreenViewModel
import presentation.screen.onboarding.startScreen.StartScreenViewModel

val presentationModule = module {
    single {
        StartScreenViewModel(
            emitOnboardingFinishedUseCase = get(),
        )
    }

    single {
        NameScreenViewModel(
            setUserNamePreferenceUseCase = get(),
        )
    }

    single {
        GoalNameScreenViewModel()
    }

    single {
        GoalFrequencyScreenViewModel(
            addGoalUseCase = get(),
            setOnboardingFinishedUseCase = get(),
        )
    }

    single {
        HomeScreenViewModel(
            emitUserNamePreferenceUseCase = get(),
            emitAllGoalsUseCase = get(),
        )
    }
}
