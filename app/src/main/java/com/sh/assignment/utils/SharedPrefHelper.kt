package com.sh.assignment.utils

import android.content.Context
import android.content.SharedPreferences

object FavoritesCharHelper {

    private const val PREF_NAME = "favorites_prefs"
    private const val KEY_FAVORITES = "favorites"

    private fun getPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    fun addFavorite(context: Context, id: Int) {
        val prefs = getPreferences(context)
        val favorites = getFavoriteIds(context).toMutableSet()
        favorites.add(id.toString())
        prefs.edit().putStringSet(KEY_FAVORITES, favorites).apply()
    }

    fun removeFavorite(context: Context, id: Int) {
        val prefs = getPreferences(context)
        val favorites = getFavoriteIds(context).toMutableSet()
        favorites.remove(id.toString())
        prefs.edit().putStringSet(KEY_FAVORITES, favorites).apply()
    }

    //favs - [1,4,5,7,8] List<Int>.toString()
    // string [1,3,5].toMutableSet()

    fun getFavoriteIds(context: Context): Set<String> {
        val prefs = getPreferences(context)
        return prefs.getStringSet(KEY_FAVORITES, emptySet()) ?: emptySet()
    }

    fun isFavorite(context: Context, id: Int): Boolean {
        return getFavoriteIds(context).contains(id.toString())
    }
}
