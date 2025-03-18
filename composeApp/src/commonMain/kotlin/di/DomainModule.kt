package di

import domain.useCase.AddGoalUseCase
import domain.useCase.DeleteGoalUseCase
import domain.useCase.EditGoalUseCase
import domain.useCase.EmitAllGoalsUseCase
import domain.useCase.EmitCounterPreferenceUseCase
import domain.useCase.EmitGoalUseCase
import domain.useCase.EmitNamePreferenceUseCase
import domain.useCase.SetCounterPreferenceUseCase
import domain.useCase.SetNamePreferenceUseCase
import org.koin.dsl.module

val domainModule = module {
    single {
        SetCounterPreferenceUseCase(
            dataStoreRepository = get(),
        )
    }

    single {
        EmitCounterPreferenceUseCase(
            dataStoreRepository = get(),
        )
    }

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
        SetNamePreferenceUseCase(
            dataStoreRepository = get(),
        )
    }

    single {
        EmitNamePreferenceUseCase(
            dataStoreRepository = get(),
        )
    }
}
