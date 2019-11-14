package io.github.luteoos.roxa.utils

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.preference.PreferenceManager
import io.github.luteoos.roxa.view.MainActivity
import java.util.*

object Session {
    private val USER_UUID = "USER_UUID"
    lateinit var preferences: SharedPreferences

    fun init(context: Context) {
        preferences = PreferenceManager.getDefaultSharedPreferences(context)
    }


    val userUUDString: String?
        get() = preferences.getString(USER_UUID, "")
    var userUUID: UUID
        get() {
            val userUUID = preferences.getString(USER_UUID, "")
            return UUID.fromString(userUUID)
        }
        set(value) {
            preferences.edit().putString(USER_UUID, value.toString()).apply()
        }

    fun logout(context: Context) {
        preferences.edit().clear().apply()
        val intent = Intent(context, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }
}