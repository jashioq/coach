package di

import domain.useCase.AddGoalUseCase
import domain.useCase.EditGoalUseCase
import domain.useCase.EmitAllGoalsUseCase
import domain.useCase.EmitOnboardingFinishedUseCase
import domain.useCase.SetOnboardingFinishedUseCase
import domain.useCase.SetUserNamePreferenceUseCase
import navigation.viewModel.NavigationViewModel
import org.koin.dsl.module
import presentation.screen.home.viewModel.HomeScreenViewModel
import presentation.screen.onboarding.goalFrequencyScreen.viewModel.GoalFrequencyScreenViewModel
import presentation.screen.onboarding.goalNameScreen.viewModel.GoalNameScreenViewModel
import presentation.screen.onboarding.nameScreen.viewModel.NameScreenViewModel
import presentation.screen.onboarding.startScreen.viewModel.StartScreenViewModel

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
            emitAllGoalsUseCase = get<EmitAllGoalsUseCase>(),
            editGoalUseCase = get<EditGoalUseCase>(),
        )
    }
}
