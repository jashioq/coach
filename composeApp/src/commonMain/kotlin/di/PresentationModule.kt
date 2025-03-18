package di

import org.koin.dsl.module
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
}
