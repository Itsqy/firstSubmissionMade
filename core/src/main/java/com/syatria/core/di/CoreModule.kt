package com.syatria.core.di

import androidx.room.Room
import com.syatria.core.utils.AppExecutors
import com.syatria.core.data.source.local.room.UserDatabase

import com.syatria.core.data.UserRepository
import com.syatria.core.data.source.local.LocalDataResource
import com.syatria.core.data.source.remote.RemoteDataSource
import com.syatria.core.data.source.remote.network.ApiService
import com.syatria.core.domain.repository.IUserRepository
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<UserDatabase>().userDao() }
    single {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("dicoding".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            UserDatabase::class.java, "user.db"
        ).fallbackToDestructiveMigration().openHelperFactory(factory).build()

    }
}

val networkModule = module {


    single {
        val hostname = "rickandmortyapi.com"
        val certifPinner = CertificatePinner.Builder()
            .add(hostname, "sha256/g/nHUe/wQuWhar3VUKpAmCsZJJvJboA0sWDG4fidbAU=")
            .add(hostname, "sha256/jQJTbIh0grw0/1TkHSumWb+Fs0Ggogr621gT3PvPKG0=")
            .add(hostname, "sha256/C5+lpZ7tcVwmwQIMcRtPbsQtWLABXhQzejna0wHFr8M=")
            .build()

        val loggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certifPinner)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()

        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataResource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IUserRepository> { UserRepository(get(), get(), get()) }
}