package di

import data.dataSource.dataBase.DriverFactory
import org.jh.coach.createAndroidDataStore
import org.jh.coach.data.local.database.Database
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

actual val dataSourceModule = module {
    single {
        createAndroidDataStore(
            context = androidContext(),
        )
    }
    single<Database> {
        Database(DriverFactory().createDriver())
    }
}
