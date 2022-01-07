package com.example.retrofitpractice.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofitpractice.model.network.ApiClient
import com.example.retrofitpractice.model.responses.Posts
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class RetrofitViewModel @Inject constructor(private val api: ApiClient) : ViewModel() {

    val postResponse: MutableLiveData<Response<List<Posts>>> = MutableLiveData()

    fun requestFeed() {

        viewModelScope.launch {

            api.getAllPosts()
                .enqueue(object : Callback<List<Posts>> {

                    override fun onResponse(
                        call: Call<List<Posts>>,
                        response: Response<List<Posts>>
                    ) {
                        if (response.isSuccessful) {

                            postResponse.postValue(response)
                        }
                    }

                    override fun onFailure(call: Call<List<Posts>>, t: Throwable) {

                        t.message?.let { Log.e("Error en Servidor", it) }
                    }
                })
        }
    }
}