package com.example.gm_challenge.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.*
import com.example.gm_challenge.model.Result
import com.example.gm_challenge.repository.ArtistRepository
import com.example.gm_challenge.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtistViewModel @Inject constructor(
    private val artistRepository: ArtistRepository
) : ViewModel() {

    private var prevQuery = ""
    private val shouldFetch: Boolean
        get() = searchQuery.value?.length ?: 0 > prevQuery.length
    var searchQuery = MutableLiveData("")

    val artists = searchQuery.distinctUntilChanged().switchMap { query ->
        val resource = if (shouldFetch) // don't fetch when user is backspacing
            artistRepository.fetchArtists(query).asLiveData()
        else liveData { Resource.Idle }
        prevQuery = query
        return@switchMap resource
    }

}