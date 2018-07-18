package com.example.rumpi.testgit.dagger

import android.app.Application
import android.content.Context
import com.example.rumpi.testgit.network.Endpoints
import com.example.rumpi.testgit.network.GSON
import com.example.rumpi.testgit.network.applyDefaultConfig
import com.example.rumpi.testgit.network.usecases.GitUsecases
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


///////////////////////////////////////////////////////////////////////////
// App Module
///////////////////////////////////////////////////////////////////////////

@Module
class AppModule(private val app: Application) {
    @Provides
    fun provideAppContext(): Context = app
}


///////////////////////////////////////////////////////////////////////////
// Net Module
///////////////////////////////////////////////////////////////////////////

@Module
class NetModule {

    @Provides
    fun provideOkHttpClient(): OkHttpClient =
            OkHttpClient.Builder().applyDefaultConfig().build()

    @Provides
    fun provideConverterFactory(): Converter.Factory = GsonConverterFactory.create(GSON)

    @Provides
    fun provideCallAdapterFactory(): CallAdapter.Factory = RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io())
}


///////////////////////////////////////////////////////////////////////////
// Base Endpoints Module
///////////////////////////////////////////////////////////////////////////

@Module
class EndpointsModule(private val baseUrl: String = "https://api.github.com") {

    @Provides
    @Singleton
    fun provideBaseEndpoints(okHttpClient: OkHttpClient,
                             converterFactory: Converter.Factory,
                             callAdapterFactory: CallAdapter.Factory
    ): Endpoints =
            Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(baseUrl)
                    .addConverterFactory(converterFactory)
                    .addCallAdapterFactory(callAdapterFactory)
                    .build()
                    .create(Endpoints::class.java)


}


///////////////////////////////////////////////////////////////////////////
// Base Usecases Modules
///////////////////////////////////////////////////////////////////////////

@Module
class BaseUsecasesModule {
    @Provides
    @Singleton
    fun provideBaseUsecases(
            endpoints: Endpoints
    ) = GitUsecases(endpoints)
}
