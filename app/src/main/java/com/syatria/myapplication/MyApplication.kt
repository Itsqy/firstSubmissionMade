package com.syatria.myapplication

import android.app.Application
import com.syatria.core.di.databaseModule
import com.syatria.core.di.networkModule
import com.syatria.core.di.repositoryModule
import com.syatria.myapplication.di.useCaseModule
import com.syatria.myapplication.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}