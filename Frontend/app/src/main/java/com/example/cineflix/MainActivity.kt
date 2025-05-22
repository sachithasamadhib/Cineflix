package com.example.cineflix

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout

class MainActivity : AppCompatActivity() {

    // Use lateinit to avoid null safety issues with view binding
    private lateinit var animationText: TextView
    private lateinit var mainLayout: ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize views
        animationText = findViewById(R.id.tv_animation)
        mainLayout = findViewById(R.id.main)

        // Start with the animation text visible and the main content invisible
        animationText.visibility = View.VISIBLE
        hideLoginContent()

        // Play the Netflix-style startup animation
        playStartupAnimation()
    }

    private fun hideLoginContent() {
        // Hide all login elements except the animation text
        for (i in 0 until mainLayout.childCount) {
            val child = mainLayout.getChildAt(i)
            if (child.id != R.id.tv_animation) {
                child.visibility = View.INVISIBLE
            }
        }
    }

    private fun showLoginContent() {
        // Show all login elements
        for (i in 0 until mainLayout.childCount) {
            val child = mainLayout.getChildAt(i)
            if (child.id != R.id.tv_animation) {
                child.visibility = View.VISIBLE
            }
        }
    }

    private fun playStartupAnimation() {
        // Fade in animation
        val fadeIn = ObjectAnimator.ofFloat(animationText, "alpha", 0f, 1f)
        fadeIn.duration = 1000

        // Scale animation
        val scaleX = ObjectAnimator.ofFloat(animationText, "scaleX", 0.8f, 1.1f)
        val scaleY = ObjectAnimator.ofFloat(animationText, "scaleY", 0.8f, 1.1f)
        scaleX.duration = 1500
        scaleY.duration = 1500

        // Create animator set
        val animatorSet = AnimatorSet()
        animatorSet.playTogether(fadeIn, scaleX, scaleY)
        animatorSet.interpolator = AccelerateDecelerateInterpolator()

        // Start animation
        animatorSet.start()

        // After animation completes, show the login screen
        Handler(Looper.getMainLooper()).postDelayed({
            // Fade out animation text
            val fadeOut = ObjectAnimator.ofFloat(animationText, "alpha", 1f, 0f)
            fadeOut.duration = 500
            fadeOut.start()

            // Show login content
            Handler(Looper.getMainLooper()).postDelayed({
                animationText.visibility = View.GONE
                showLoginContent()
            }, 500)
        }, 2500)
    }
}