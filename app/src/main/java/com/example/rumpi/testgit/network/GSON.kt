package com.example.rumpi.testgit.network

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder


///////////////////////////////////////////////////////////////////////////
// GSON Config
///////////////////////////////////////////////////////////////////////////

val GSON: Gson by lazy {
    with(GsonBuilder()) {
        serializeNulls()
        setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
    }.create()
}