package dev.ogabek.android_saa.networking

import dev.ogabek.android_saa.model.Image
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {

    @Headers("Authorization: Client-ID 5GhEYVZz8Fpt9z0dCoxbNWBq1VTHeEQC5nRxgrgrSIg")
    @GET("/photos")
    suspend fun getPhotos(
        @Query("page") page: Int,
        @Query("per_page") per_page: Int
    ): Response<ArrayList<Image>>

}