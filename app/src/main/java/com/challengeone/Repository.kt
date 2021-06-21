package com.challengeone

import com.challengeone.model.ResponseModel
import com.challengeone.network.RetrofitClient
import retrofit2.Response

class Repository {

    suspend fun getCountrylist(): Response<ArrayList<ResponseModel>> {
        return RetrofitClient().getapi().getCountries()
    }
}