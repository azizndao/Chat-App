package com.zzizzand.chatapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    companion object {
        val auth = FirebaseAuth.getInstance()
        const val signInRequestCode: Int = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
    }

    override fun onStart() {
        super.onStart()
//        auth.addAuthStateListener {
//            if (it.currentUser == null) signIn()
//            else signIn()
//        }
    }

    private fun signIn() {
        startActivityForResult(
            Intent(this, SignInActivity::class.java),
            signInRequestCode
        )
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_settings -> true
        R.id.action_search -> true
        R.id.action_sign_out -> {
            auth.signOut()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == signInRequestCode) {
            if (resultCode == Activity.RESULT_CANCELED) finish()
        }
    }
}
