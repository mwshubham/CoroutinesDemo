package com.example.coroutinesdemo

import android.util.Log

/**
 * This Class is just to display log messages
 *
 * --to see the log under this particular Tag in Info
 * --Message you want to print.
 *
 * -- Adding support to null as all the this class is also used in the ${DeBug} class
 * which is not null safe compatible...
 *
 */
object GlobalLoggerUtils {

    private val toShowLog = BuildConfig.DEBUG

    @JvmStatic
    fun showLog(TAG: String?, message: String?) {
        try {
            if (toShowLog && TAG != null && message != null) {
                Log.i(TAG, message + " " + "[Thread: " + Thread.currentThread().name + "]")
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}