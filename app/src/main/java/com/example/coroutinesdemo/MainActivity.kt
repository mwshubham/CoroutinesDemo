package com.example.coroutinesdemo

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

/**
 * Reference: https://blog.mindorks.com/mastering-kotlin-coroutines-in-android-step-by-step-guide
 */
class MainActivity : AppCompatActivity(), CoroutineScope {

    companion object {
        val TAG: String = MainActivity::class.java.simpleName
    }

    private lateinit var job: Job

    private val handler = CoroutineExceptionHandler { _, exception ->
        GlobalLoggerUtils.showLog(TAG, "$exception handled !")
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job + handler


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        job = Job()

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()

            launch {
                GlobalLoggerUtils.showLog(TAG, "launch()")
                fetchAndShowUser()
            }
        }
    }


    private suspend fun fetchAndShowUser() {
        GlobalLoggerUtils.showLog(TAG, "fetchAndShowUser()")
        coroutineScope {
            val deferredUser = async(Dispatchers.IO) {
                fetchUser()
            }
            val user = deferredUser.await()
            GlobalLoggerUtils.showLog(TAG, "user: $user")
            showUser()
        }
    }

    private fun fetchUser(): Boolean {
        GlobalLoggerUtils.showLog(TAG, "fetchUser()")
//        throw Exception("Testing Exception fetchUser()")
        return false
    }

    private fun showUser() {
        GlobalLoggerUtils.showLog(TAG, "showUser()")
//        throw Exception("Testing Exception showUser()")
    }


    // same this using withContext()
    // withContext is nothing but an another way writing the async where we do not have to write await().
//        return withContext(Dispatchers.IO) {
//            GlobalLoggerUtils.showLog(TAG, "fetchAndShowUser() >>> async()")
//            false
//        }

    override fun onDestroy() {
        job.cancel() // cancel the Job
        super.onDestroy()
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
