package com.adriano.whatsapppro

import retrofit2.http.GET

interface BoredService {

    @GET("activity/")
    suspend fun getActivity(): BoredActivity
}