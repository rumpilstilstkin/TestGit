package com.example.rumpi.testgit.network

import com.example.rumpi.testgit.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit


///////////////////////////////////////////////////////////////////////////
// Timeout constants
///////////////////////////////////////////////////////////////////////////

const val TIMEOUT_CONNECT = 120L
const val TIMEOUT_READ = 120L
const val TIMEOUT_WRITE = 120L


///////////////////////////////////////////////////////////////////////////
// Default Config Helper
///////////////////////////////////////////////////////////////////////////

fun OkHttpClient.Builder.applyDefaultConfig(): OkHttpClient.Builder = apply {
    connectTimeout(TIMEOUT_CONNECT, TimeUnit.SECONDS)
    readTimeout(TIMEOUT_READ, TimeUnit.SECONDS)

    if (BuildConfig.DEBUG) {
        addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
    }
}
