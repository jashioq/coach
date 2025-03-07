package di

import org.koin.dsl.module
import presentation.screenA.ScreenAViewModel

val presentationModule = module {
    single {
        ScreenAViewModel(
            setCounterPreferenceUseCase = get(),
            emitCounterPreferenceUseCase = get(),
        )
    }
}
