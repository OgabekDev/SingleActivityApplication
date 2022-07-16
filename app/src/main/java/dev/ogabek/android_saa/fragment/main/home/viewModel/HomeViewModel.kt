package dev.ogabek.android_saa.fragment.main.home.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.ogabek.android_saa.model.Image
import dev.ogabek.android_saa.utils.UiStateList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException

class HomeViewModel(private val repository: HomeRepository) : ViewModel() {

    private val _images = MutableStateFlow<UiStateList<Image>>(UiStateList.EMPTY)
    val images = _images

    fun getImages(page: Int) = viewModelScope.launch {
        _images.value = UiStateList.LOADING
        try {
            val images = repository.getImages(page)
            if (images.isSuccessful) {
                _images.value = UiStateList.SUCCESS(images.body()!!)
            } else {
                _images.value = UiStateList.ERROR(
                    images.code().toString() + " " + images.message() + " " + images.errorBody()
                )
            }
        } catch (e: HttpException) {
            e.printStackTrace()
            _images.value = UiStateList.ERROR(e.localizedMessage ?: "No Connection")
        }
    }

}