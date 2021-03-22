package br.com.fernandomflopes.googlebooks.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.fernandomflopes.googlebooks.model.BookHttp
import br.com.fernandomflopes.googlebooks.model.Volume
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BookListViewModel : ViewModel(){

    private val _state = MutableLiveData<State>()
    val state: LiveData<State>
        get() = _state

    fun loadBooks(apiKey: String) {
        if(_state.value is State.Loaded)
            return

        viewModelScope.launch {
            _state.value = State.Loading
            val result = withContext(Dispatchers.IO) {
                BookHttp.searchBook(apiKey, "Dominando o android")
            }

            if(result?.items == null) {
                _state.value = State.Error(Exception("Error on loading books"), false)
            } else {
                _state.value = State.Loaded(result.items)
            }
        }
    }

    sealed class State {
        object Loading: State()
        data class Loaded(val items: List<Volume>): State()
        data class Error(val e: Throwable, var hasConsumed: Boolean): State()
    }

}