package dev.ogabek.android_saa.fragment.main.home.viewModel

import dev.ogabek.android_saa.networking.ApiService

class HomeRepository(val apiService: ApiService) {

    suspend fun getImages(page: Int) = apiService.getPhotos(page, 25)

}