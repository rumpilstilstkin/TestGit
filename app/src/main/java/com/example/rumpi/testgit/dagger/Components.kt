package com.example.rumpi.testgit.dagger

import android.content.Context
import com.example.rumpi.testgit.network.Endpoints
import com.example.rumpi.testgit.network.usecases.GitUsecases
import dagger.Component
import javax.inject.Singleton


///////////////////////////////////////////////////////////////////////////
// App Component
///////////////////////////////////////////////////////////////////////////

@Singleton
@Component(modules = [(AppModule::class), (NetModule::class), (EndpointsModule::class), (BaseUsecasesModule::class)])
interface GitAppComponent {
    fun appContext(): Context
    fun baseEndpoints(): Endpoints
    fun baseUsecases(): GitUsecases
}