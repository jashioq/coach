package di

import domain.useCase.AddGoalUseCase
import domain.useCase.DeleteGoalUseCase
import domain.useCase.EditGoalUseCase
import domain.useCase.EmitAllGoalsUseCase
import domain.useCase.EmitGoalUseCase
import domain.useCase.EmitOnboardingFinishedUseCase
import domain.useCase.EmitUserNamePreferenceUseCase
import domain.useCase.SetOnboardingFinishedUseCase
import domain.useCase.SetUserNamePreferenceUseCase
import org.koin.dsl.module

val domainModule = module {
    factory {
        EmitAllGoalsUseCase(
            dataBaseRepository = get(),
        )
    }

    factory {
        EmitGoalUseCase(
            dataBaseRepository = get(),
        )
    }

    factory {
        AddGoalUseCase(
            dataBaseRepository = get(),
        )
    }

    factory {
        EditGoalUseCase(
            dataBaseRepository = get(),
        )
    }

    factory {
        DeleteGoalUseCase(
            dataBaseRepository = get(),
        )
    }

    factory {
        SetUserNamePreferenceUseCase(
            dataStoreRepository = get(),
        )
    }

    factory {
        EmitUserNamePreferenceUseCase(
            dataStoreRepository = get(),
        )
    }

    factory {
        SetOnboardingFinishedUseCase(
            dataStoreRepository = get(),
        )
    }

    factory {
        EmitOnboardingFinishedUseCase(
            dataStoreRepository = get(),
        )
    }
}
