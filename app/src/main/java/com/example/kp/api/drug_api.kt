package com.example.kp.api

// DrugApi.kt
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface DrugApi {

    // GET /drug/label.json?search=...&limit=...
    @GET("drug/label.json")
    suspend fun searchByActiveIngredient(
        @Query("search") search: String,
        @Query("limit") limit: Int = 10
    ): Response<DrugLabelResponse>
}
