package com.example.rumpi.testgit.network

import com.example.rumpi.testgit.models.RepModel
import com.example.rumpi.testgit.models.ResponseRepsModel
import com.example.rumpi.testgit.models.UserModel
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Endpoints {

    @GET("/search/repositories")
    fun searchReps(
            @Query("q") query: String
    ): Flowable<ResponseRepsModel>

    @GET("/repositories")
    fun getAllReps(): Flowable<List<RepModel>>

    @GET("/users/{username}")
    fun getUser(
            @Path("username") username: String
    ): Flowable<UserModel>

}