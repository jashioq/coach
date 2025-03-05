package di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import presentation.screenA.ScreenAViewModel

actual val viewModelModule = module {
    viewModelOf(::ScreenAViewModel)
}
