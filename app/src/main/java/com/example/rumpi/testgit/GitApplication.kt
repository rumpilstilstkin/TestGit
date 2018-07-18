package com.example.rumpi.testgit

import android.app.Application
import android.os.StrictMode
import com.example.rumpi.testgit.dagger.AppModule
import com.example.rumpi.testgit.dagger.DaggerGitAppComponent
import com.example.rumpi.testgit.dagger.GitAppComponent
import kotlin.system.measureTimeMillis


///////////////////////////////////////////////////////////////////////////
// Git Application
/////////////////////////////////;//////////////////////////////////////////

class GitApplication: Application(){

    companion object {
        private lateinit var _appModule: AppModule
        val appModule: AppModule
            get() = _appModule

        private val _appComponent: GitAppComponent by lazy {
            DaggerGitAppComponent.builder()
                    .appModule(appModule)
                    .build()
        }

        @JvmStatic
        val appComponent: GitAppComponent
            get() = _appComponent
    }

    override fun onCreate() {
        super.onCreate()
        StrictMode.enableDefaults()
        initDagger()
    }

    private fun initDagger() {
        measureTimeMillis {
            _appModule = AppModule(this)
        }
    }
}


