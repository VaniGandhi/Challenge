package com.challengeone.network

import com.challengeone.model.ResponseModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {


    @GET("v2/all")
    suspend fun  getCountries():Response<ArrayList<ResponseModel>>
}