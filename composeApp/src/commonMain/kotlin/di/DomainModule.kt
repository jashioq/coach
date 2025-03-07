package di

import domain.useCase.EmitCounterPreferenceUseCase
import domain.useCase.SetCounterPreferenceUseCase
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
}
