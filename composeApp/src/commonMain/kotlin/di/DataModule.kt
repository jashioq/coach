package di

import data.repository.DataBaseRepository
import data.repository.DataStoreRepository
import org.koin.dsl.module

val dataModule = module {
    single<domain.repository.DataStoreRepository> {
        DataStoreRepository(
            dataStore = get(),
        )
    }
    single<domain.repository.DataBaseRepository> {
        DataBaseRepository(
            database = get(),
        )
    }
}
