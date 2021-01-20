package com.panchalamitr.oxforddictionary.viewmodel

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.panchalamitr.oxforddictionary.model.Movie
import com.panchalamitr.oxforddictionary.repository.MovieListRepository
import kotlinx.coroutines.launch

class MovieListViewModel @ViewModelInject constructor(
    private val movieListRepository: MovieListRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val movieListLiveData = MutableLiveData<Movie>()
    private val showProgressLiveData = MutableLiveData<Boolean>()
    private val errorMessageLiveData = MutableLiveData<String>()


    fun getMovie() {
        viewModelScope.launch {
            showProgressLiveData.postValue(true)
            val movieResponse = movieListRepository.getMovie()
            if(movieResponse.response == "False"){
                errorMessageLiveData.postValue(movieResponse.error)
            }else {
                movieListLiveData.postValue(movieResponse)
            }
            showProgressLiveData.postValue(false)
        }
    }

    fun observeMovieList(): MutableLiveData<Movie> {
        return movieListLiveData
    }

    fun observeProgress(): MutableLiveData<Boolean> {
        return showProgressLiveData
    }

    fun observeErrorMessage(): MutableLiveData<String> {
        return errorMessageLiveData
    }
}