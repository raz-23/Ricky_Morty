package com.sh.assignment.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.sh.assignment.api.ApiService
import com.sh.assignment.models.Characters
import com.sh.assignment.models.Result

class ApiService (
    private val api : ApiService
) {

    private val _characters = MutableLiveData<Characters>()
    val characters = _characters

    private val _character = MutableLiveData<Result>()
    val character = _character

    suspend fun getCharacters(page :Int){
        val result = api.getCharacters(page)
        Log.d("Page",page.toString())
        if(result.body() != null){
            _characters.postValue(result.body())
            Log.d("Result",result.body().toString())
        }else{
            Log.d("Error",result.toString())
        }
    }

    suspend fun getCharacter(id :Int) {
        val result = api.getCharacter(id)
        if (result.body() != null) {
            _character.postValue(result.body())
            Log.d("Character", result.body().toString() + " URL " + result.toString())
        } else {
            Log.d("Error", result.toString())
        }
    }

}