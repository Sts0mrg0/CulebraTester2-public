package com.dtmilano.android.culebratester2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.dtmilano.android.culebratester2.utils.PackageUtils
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        main_message.text = getString(R.string.main_message_default)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

    }

    override fun onResume() {
        super.onResume()
        checkInstrumentationPresent()
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

    private fun checkInstrumentationPresent() {
        val instrumentationInfo = PackageUtils.isInstrumentationPresent(this)
        if (instrumentationInfo != null) {
            // Do something
            // We are resuming and instrumentation is installed, so we should start the tests, however it's not
            // possible from the Activity
            main_message.append("\n" + getString(R.string.msg_instrumentation_installed))
        } else {
            val snackbar = Snackbar.make(
                coordinator,
                R.string.msg_instrumentation_not_installed,
                Snackbar.LENGTH_INDEFINITE
            ).setAction(R.string.action_install) {
                // FIXME: can we install from github?
            }
            snackbar.show()
        }
    }

    companion object {
        /**
         * Convenience function to start the MainActivity.
         */
        fun start(context: Context) {
            val main = Intent(Intent.ACTION_MAIN)
            main.setClass(context, MainActivity::class.java)
            context.startActivity(main)
        }
    }
}
