package com.panchalamitr.oxforddictionary.viewmodel

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.panchalamitr.oxforddictionary.model.MovieDetail
import com.panchalamitr.oxforddictionary.repository.MovieDetailRepository
import kotlinx.coroutines.launch

class MovieDetailViewModel @ViewModelInject constructor(
    private val movieDetailRepository: MovieDetailRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val movieDetailLiveData = MutableLiveData<MovieDetail>()
    private val showProgressLiveData = MutableLiveData<Boolean>()
    private val errorMessageLiveData = MutableLiveData<String>()


    fun getMovieDetail(imDbId: String) {
        viewModelScope.launch {
            showProgressLiveData.postValue(true)
            val movieResponse = movieDetailRepository.getMovieDetail(imDbId)
            if(movieResponse.response == "False"){
                errorMessageLiveData.postValue(movieResponse.error)
            }else {
                movieDetailLiveData.postValue(movieResponse)
            }
            showProgressLiveData.postValue(false)
        }
    }

    fun observeMovieDetail(): MutableLiveData<MovieDetail> {
        return movieDetailLiveData
    }

    fun observeProgress(): MutableLiveData<Boolean> {
        return showProgressLiveData
    }

    fun observeErrorMessage(): MutableLiveData<String> {
        return errorMessageLiveData
    }
}