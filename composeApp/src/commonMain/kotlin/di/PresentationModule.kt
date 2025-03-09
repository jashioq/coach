package di

import org.koin.dsl.module
import presentation.screenA.ScreenAViewModel
import presentation.screenB.ScreenBViewModel

val presentationModule = module {
    single {
        ScreenAViewModel(
            setCounterPreferenceUseCase = get(),
            emitCounterPreferenceUseCase = get(),
        )
    }
    single {
        ScreenBViewModel(
            emitAllPostsUseCase = get(),
        )
    }
}
