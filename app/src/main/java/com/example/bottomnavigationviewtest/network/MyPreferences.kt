import android.content.Context
import android.content.SharedPreferences

object MyPreferences {
    private const val PREF_NAME = "my_prefs"
    private const val TOKEN_KEY = "token_key"
    private const val EMAIL_KEY = "email_key"
    private const val NICKNAME_KEY = "nickname_key"
    var profileExist : Boolean = false

    private fun getSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    fun saveToken(context: Context, token: String) {
        val editor = getSharedPreferences(context).edit()
        editor.putString(TOKEN_KEY, token)
        editor.apply()
    }

    fun saveEmail(context: Context, email: String) {
        val editor = getSharedPreferences(context).edit()
        editor.putString(EMAIL_KEY, email)
        editor.apply()
    }

    fun saveNickname(context: Context, nickname: String) {
        val editor = getSharedPreferences(context).edit()
        editor.putString(NICKNAME_KEY, nickname)
        editor.apply()
    }

    fun getToken(context: Context): String? {
        return getSharedPreferences(context).getString(TOKEN_KEY, null)
    }

    fun getEmail(context: Context): String? {
        return getSharedPreferences(context).getString(EMAIL_KEY, null)
    }

    fun getNickname(context: Context): String? {
        return getSharedPreferences(context).getString(NICKNAME_KEY, null)
    }

}
