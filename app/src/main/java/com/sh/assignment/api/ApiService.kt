package com.sh.assignment.api

import com.sh.assignment.models.Characters
import com.sh.assignment.models.Result
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("api/character/")
    suspend fun getCharacters(@Query("page") page : Int):Response<Characters>

    @GET("api/character/{id}")
    suspend fun getCharacter(@Path("id") id:Int):Response<Result>
}
