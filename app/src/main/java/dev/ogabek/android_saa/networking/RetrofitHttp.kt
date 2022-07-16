package dev.ogabek.android_saa.networking

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitHttp {

    companion object {

        const val IS_TESTER = true;

        private const val SERVER_DEVELOPMENT = "https://api.unsplash.com/"
        private const val SERVER_PRODUCTION = "https://api.unsplash.com/"

        private fun server(): String {
            return if (IS_TESTER) {
                SERVER_DEVELOPMENT
            } else {
                SERVER_PRODUCTION
            }
        }

        private fun getRetrofit(): Retrofit {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(server())
                .build()
        }

        val apiService: ApiService = getRetrofit().create(ApiService::class.java)

    }

}