package di

import domain.useCase.AddGoalUseCase
import domain.useCase.EmitAllGoalsUseCase
import domain.useCase.EmitOnboardingFinishedUseCase
import domain.useCase.EmitUserNamePreferenceUseCase
import domain.useCase.SetOnboardingFinishedUseCase
import domain.useCase.SetUserNamePreferenceUseCase
import navigation.viewModel.NavigationViewModel
import org.koin.dsl.module
import presentation.screen.home.HomeScreenViewModel
import presentation.screen.onboarding.goalFrequencyScreen.GoalFrequencyScreenViewModel
import presentation.screen.onboarding.goalNameScreen.GoalNameScreenViewModel
import presentation.screen.onboarding.nameScreen.NameScreenViewModel
import presentation.screen.onboarding.startScreen.StartScreenViewModel

val presentationModule = module {
    factory {
        StartScreenViewModel()
    }

    factory {
        NameScreenViewModel(
            setUserNamePreferenceUseCase = get<SetUserNamePreferenceUseCase>(),
        )
    }

    factory {
        GoalNameScreenViewModel()
    }

    factory {
        GoalFrequencyScreenViewModel(
            addGoalUseCase = get<AddGoalUseCase>(),
            setOnboardingFinishedUseCase = get<SetOnboardingFinishedUseCase>(),
        )
    }

    factory {
        NavigationViewModel(
            emitOnboardingFinishedUseCase = get<EmitOnboardingFinishedUseCase>(),
        )
    }

    factory {
        HomeScreenViewModel(
            emitUserNamePreferenceUseCase = get<EmitUserNamePreferenceUseCase>(),
            emitAllGoalsUseCase = get<EmitAllGoalsUseCase>(),
        )
    }
}
