package di

import domain.useCase.AddGoalUseCase
import domain.useCase.EmitAllGoalsUseCase
import domain.useCase.EmitOnboardingFinishedUseCase
import domain.useCase.EmitUserNamePreferenceUseCase
import domain.useCase.SetOnboardingFinishedUseCase
import domain.useCase.SetUserNamePreferenceUseCase
import org.koin.dsl.module
import presentation.screen.home.HomeScreenViewModel
import presentation.screen.onboarding.goalFrequencyScreen.GoalFrequencyScreenViewModel
import presentation.screen.onboarding.goalNameScreen.GoalNameScreenViewModel
import presentation.screen.onboarding.nameScreen.NameScreenViewModel
import presentation.screen.onboarding.startScreen.StartScreenViewModel

val presentationModule = module {
    single {
        StartScreenViewModel(
            emitOnboardingFinishedUseCase = get<EmitOnboardingFinishedUseCase>(),
        )
    }

    single {
        NameScreenViewModel(
            setUserNamePreferenceUseCase = get<SetUserNamePreferenceUseCase>(),
        )
    }

    single {
        GoalNameScreenViewModel()
    }

    single {
        GoalFrequencyScreenViewModel(
            addGoalUseCase = get<AddGoalUseCase>(),
            setOnboardingFinishedUseCase = get<SetOnboardingFinishedUseCase>(),
        )
    }

    single {
        HomeScreenViewModel(
            emitUserNamePreferenceUseCase = get<EmitUserNamePreferenceUseCase>(),
            emitAllGoalsUseCase = get<EmitAllGoalsUseCase>(),
        )
    }
}
