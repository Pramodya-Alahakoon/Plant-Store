package com.example.botaniq

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide

class OnboardingActivity : AppCompatActivity() {
    private lateinit var onboardingViewPagerAdapter: OnboardingViewPagerAdapter
    private lateinit var layoutDots: LinearLayout
    private lateinit var btnGetStarted: Button
    private lateinit var gifImageView: ImageView  // For displaying the GIF

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        btnGetStarted = findViewById(R.id.btnGetStarted)
        layoutDots = findViewById(R.id.layoutDots)
        gifImageView = findViewById(R.id.gifImageView)  // Reference to the GIF ImageView

        setOnboardingItems()
        setupIndicators()
        setCurrentIndicator(0)
    }

    private fun setOnboardingItems() {
        onboardingViewPagerAdapter = OnboardingViewPagerAdapter(
            listOf(
                OnboardingItem(R.drawable.onboarding1, "Welcome To Leafy!", "Welcome to the world of lush greens and blooming beauty with us."),
                OnboardingItem(R.drawable.onboarding2, "Bring Nature Home", "Let's make your first purchase seamless."),
                OnboardingItem(R.drawable.onboarding3, "Discover Your Green Heaven", "It's time to discover the incredible variety of plants waiting for you.")
            )
        )

        val onboardingViewPager = findViewById<ViewPager2>(R.id.screenPager)
        onboardingViewPager.adapter = onboardingViewPagerAdapter

        onboardingViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentIndicator(position)
                if (position == onboardingViewPagerAdapter.itemCount - 1) {
                    btnGetStarted.visibility = View.VISIBLE
                } else {
                    btnGetStarted.visibility = View.GONE
                }
            }
        })

        btnGetStarted.setOnClickListener {
            playGifAndRedirect()  // Function to show GIF and redirect
        }
    }

    private fun playGifAndRedirect() {
        gifImageView.visibility = View.VISIBLE // Show GIF
        Glide.with(this).load(R.drawable.fyf).into(gifImageView) // Load GIF with Glide

        // Delay for 200ms, then navigate to LoginActivity
        Handler().postDelayed({
            gifImageView.visibility = View.GONE // Hide GIF
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }, 1)
    }

    private fun setupIndicators() {
        val indicators = arrayOfNulls<ImageView>(onboardingViewPagerAdapter.itemCount)
        val layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        layoutParams.setMargins(8, 0, 8, 0)
        for (i in indicators.indices) {
            indicators[i] = ImageView(applicationContext)
            indicators[i]?.let {
                it.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.indicator_inactive))
                it.layoutParams = layoutParams
                layoutDots.addView(it)
            }
        }
    }

    private fun setCurrentIndicator(position: Int) {
        val childCount = layoutDots.childCount
        for (i in 0 until childCount) {
            val imageView = layoutDots.getChildAt(i) as ImageView
            if (i == position) {
                imageView.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.indicator_active))
            } else {
                imageView.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.indicator_inactive))
            }
        }
    }
}
