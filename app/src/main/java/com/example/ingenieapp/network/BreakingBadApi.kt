package com.example.ingenieapp.network

import com.example.ingenieapp.model.CharacterModel
import retrofit2.Response
import retrofit2.http.GET


enum class APIStatus { LOADING, ERROR, DONE }

class Constants {
    companion object {
        const val BASE_URL = "https://breakingbadapi.com/api/"
    }
}

interface BreakingBadApi {
    @GET("characters")
    suspend fun getCharacters(): List<CharacterModel>
}