package ksr.android.compose.common

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes

class ToastUtil {
    companion object {
        @JvmStatic
        fun showShort(@StringRes stringRes: Int, ctx: Context? = null) {
            val context = ctx ?: GlobalApplication.applicationCtx
            Toast.makeText(context, stringRes, Toast.LENGTH_SHORT).show()
        }

        @JvmStatic
        fun showShort(string: String, ctx: Context? = null) {
            val context = ctx ?: GlobalApplication.applicationCtx
            Toast.makeText(context, string, Toast.LENGTH_SHORT).show()
        }

        @JvmStatic
        fun showLong(@StringRes stringRes: Int, ctx: Context? = null) {
            val context = ctx ?: GlobalApplication.applicationCtx
            Toast.makeText(context, stringRes, Toast.LENGTH_LONG).show()
        }

        @JvmStatic
        fun showLong(string: String, ctx: Context? = null) {
            val context = ctx ?: GlobalApplication.applicationCtx
            Toast.makeText(context, string, Toast.LENGTH_LONG).show()
        }

    }

}