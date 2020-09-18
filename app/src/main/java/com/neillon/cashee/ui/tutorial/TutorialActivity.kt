package com.neillon.cashee.ui.tutorial

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.neillon.cashee.R
import com.neillon.cashee.databinding.ActivityTutorialBinding
import com.neillon.cashee.store.TutorialDataStore
import com.neillon.cashee.ui.MainActivity
import com.neillon.cashee.ui.tutorial.adapter.TutorialAdapter
import com.neillon.cashee.utils.callActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class TutorialActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTutorialBinding
    private val tutorialDataStore by lazy { TutorialDataStore(this) }
    val tutorialAdapter = TutorialAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTutorialBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupTutorialPages()
        setupViews()
    }

    private fun setupViews() {
        binding.buttonSkip.setOnClickListener {
            // TODO: Go to login
        }
        binding.buttonNext.setOnClickListener {
            if (binding.viewPagerTutorial.currentItem == tutorialAdapter.itemCount - 1) {
                // TODO: Go to login
            } else {
                binding.viewPagerTutorial.currentItem += 1
            }
        }
    }

    private fun setupTutorialPages() {
        binding.viewPagerTutorial.adapter = tutorialAdapter
        binding.viewPagerTutorial.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position == tutorialAdapter.itemCount - 1) {
                    binding.buttonNext.text = getString(R.string.start)
                    binding.buttonSkip.isVisible = false
                } else {
                    binding.buttonNext.text = getString(R.string.next)
                    binding.buttonSkip.isVisible = true
                }
            }
        })
    }

}