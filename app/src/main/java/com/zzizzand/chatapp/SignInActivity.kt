package com.zzizzand.chatapp

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.ContentLoadingProgressBar
import androidx.core.widget.doOnTextChanged
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.*
import com.zzizzand.chatapp.utils.isValidEmail
import com.zzizzand.chatapp.utils.makeStatusBarTransparent
import kotlinx.android.synthetic.main.activity_sigin_in.*


class SignInActivity : AppCompatActivity() {
    private lateinit var signInButton: MaterialButton
    private val mAuth = FirebaseAuth.getInstance()
    private lateinit var emailField: TextInputEditText
    private lateinit var passwordFiled: TextInputEditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sigin_in)
        emailField = findViewById(R.id.email)
        signInButton = findViewById<MaterialButton>(R.id.button_sign_in).apply {
            isEnabled = emailField.isActivated && passwordFiled.isActivated
            setOnClickListener { signUp() }
        }
        passwordFiled = findViewById<TextInputEditText>(R.id.password).apply {
            doOnTextChanged { _, _, _, after ->
                signInButton.isEnabled = emailField.text.toString().isValidEmail() && after >= 6
            }
            setOnKeyListener { _, keyCode, _ ->
                if (keyCode == 66) {
                    signUp()
                    return@setOnKeyListener true
                }

                false
            }
        }
        makeStatusBarTransparent()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishActivity(Activity.RESULT_CANCELED)
    }

    private fun signUp() {
        signInButton.visibility = View.GONE
        progressBar.visibility = View.VISIBLE
        val email = emailField.text.toString()
        val password = passwordFiled.text.toString()
        try {
            mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        updateUI()
                    } else {
                        if (task.exception is FirebaseAuthUserCollisionException) {
                            signIn()
                            return@addOnCompleteListener
                        }
                        Toast.makeText(
                            this,
                            task.exception?.message,
                            Toast.LENGTH_LONG
                        ).show()
                    }

                    progressBar.visibility = View.GONE
                    button_sign_in.visibility = View.VISIBLE
                }
        } catch (e: FirebaseAuthWeakPasswordException) {
            Toast.makeText(
                this, e.message,
                Toast.LENGTH_LONG
            ).show()
        } catch (e: FirebaseAuthInvalidCredentialsException) {
            Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
        } catch (e: FirebaseAuthUserCollisionException) {
            signIn()
        }
    }

    private fun signIn() {
        val email = emailField.text.toString()
        val password = passwordFiled.text.toString()
        try {
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    updateUI()
                } else {
                    Toast.makeText(this, task.exception?.message, Toast.LENGTH_LONG).show()
                }
                progressBar.visibility = View.GONE
                button_sign_in.visibility = View.VISIBLE
            }
        } catch (e: FirebaseAuthInvalidUserException) {
            Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
        } catch (e: FirebaseAuthInvalidCredentialsException) {
            Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
        }
    }

    private fun updateUI() {
        Toast.makeText(this, "Sign in success.", Toast.LENGTH_LONG).show()
        setResult(Activity.RESULT_OK)
        finish()
    }
}
