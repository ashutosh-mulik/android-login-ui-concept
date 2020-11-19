package com.ashutosh.loginuiconcept

import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import android.view.View.OnFocusChangeListener
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView
import com.google.android.material.progressindicator.ProgressIndicator
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {

    private var validEmail = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val animationView : LottieAnimationView = findViewById(R.id.loginAnimationView)
        val layoutEmail: TextInputLayout = findViewById(R.id.layoutInpEmail)
        val layoutPassword: TextInputLayout = findViewById(R.id.layoutInpPassword)
        val inpEmail: TextInputEditText = findViewById(R.id.inpEmail)
        val inpPassword: TextInputEditText = findViewById(R.id.inpPassword)
        val progressIndicator: ProgressIndicator = findViewById(R.id.progressBar)
        val btnLogin: Button = findViewById(R.id.btnLogin)

        animationView.setMinAndMaxFrame(0, 100)
        animationView.playAnimation()

        inpEmail.onFocusChangeListener = OnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                if (inpEmail.text.toString().isEmpty()) {
                    animationView.setAnimation(R.raw.expressionless)
                    animationView.setMinAndMaxFrame(0, 120)
                    animationView.playAnimation()
                    animationView.loop(true)
                }
            } else {
                if (isValidEmail(inpEmail.text.toString())) {
                    validEmail = true
                    layoutEmail.error = null
                } else {
                    validEmail = false
                    animationView.setAnimation(R.raw.sad)
                    animationView.playAnimation()
                    animationView.loop(true)
                    layoutEmail.error = "Please Specify Valid Email."
                }
            }
        }

        inpPassword.onFocusChangeListener = OnFocusChangeListener { _, hasFocus ->
            if (hasFocus && validEmail) {
                animationView.setAnimation(R.raw.cool)
                animationView.playAnimation()
                animationView.loop(false)
            }
        }

        // Button Login
        btnLogin.setOnClickListener(View.OnClickListener {

            if (inpEmail.text.toString().isEmpty()) {
                animationView.setAnimation(R.raw.sad)
                animationView.playAnimation()
                animationView.loop(true)
                layoutEmail.error = "Please Specify Email."
                return@OnClickListener
            } else {
                layoutEmail.error = null
            }

            if (!isValidEmail(inpEmail.text.toString())) {
                animationView.setAnimation(R.raw.sad)
                animationView.playAnimation()
                animationView.loop(true)
                layoutEmail.error = "Please Specify Valid Email."
                return@OnClickListener
            } else {
                layoutEmail.error = null
            }

            if (inpPassword.text.toString().isEmpty()) {
                animationView.setAnimation(R.raw.sad)
                animationView.playAnimation()
                animationView.loop(true)
                layoutPassword.error = "Please Specify Password."
                return@OnClickListener
            } else {
                layoutPassword.error = null
            }

            animationView.setAnimation(R.raw.detective)
            animationView.playAnimation()
            animationView.loop(true)
            progressIndicator.visibility = View.VISIBLE

        })

    }

    private fun isValidEmail(target: CharSequence): Boolean {
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches()
    }
}