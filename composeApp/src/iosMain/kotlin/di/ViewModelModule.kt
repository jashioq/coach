package di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import presentation.screenA.ScreenAViewModel

actual val viewModelModule = module {
    singleOf(::ScreenAViewModel)
}