package di

import createIosDataStore
import data.dataSource.dataBase.DriverFactory
import org.jh.coach.data.local.database.Database
import org.koin.dsl.module

actual val dataSourceModule = module {
    single {
        createIosDataStore()
    }
    single<Database> {
        Database(DriverFactory().createDriver())
    }
}