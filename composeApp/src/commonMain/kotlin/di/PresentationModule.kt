package di

import org.koin.dsl.module
import presentation.screen.onboarding.goalFrequencyScreen.GoalFrequencyScreenViewModel
import presentation.screen.onboarding.goalNameScreen.GoalNameScreenViewModel
import presentation.screen.onboarding.nameScreen.NameScreenViewModel
import presentation.screen.onboarding.startScreen.StartScreenViewModel

val presentationModule = module {
    single {
        StartScreenViewModel()
    }

    single {
        NameScreenViewModel(
            setNamePreferenceUseCase = get(),
        )
    }

    single {
        GoalNameScreenViewModel(
            setGoalNamePreferenceUseCase = get(),
        )
    }

    single {
        GoalFrequencyScreenViewModel(
            addGoalUseCase = get(),
        )
    }
}
