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
    single {
        EmitAllGoalsUseCase(
            dataBaseRepository = get(),
        )
    }

    single {
        EmitGoalUseCase(
            dataBaseRepository = get(),
        )
    }

    single {
        AddGoalUseCase(
            dataBaseRepository = get(),
        )
    }

    single {
        EditGoalUseCase(
            dataBaseRepository = get(),
        )
    }

    single {
        DeleteGoalUseCase(
            dataBaseRepository = get(),
        )
    }

    single {
        SetUserNamePreferenceUseCase(
            dataStoreRepository = get(),
        )
    }

    single {
        EmitUserNamePreferenceUseCase(
            dataStoreRepository = get(),
        )
    }

    single {
        SetOnboardingFinishedUseCase(
            dataStoreRepository = get(),
        )
    }

    single {
        EmitOnboardingFinishedUseCase(
            dataStoreRepository = get(),
        )
    }
}
