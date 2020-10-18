package com.neillon.cashee.authentication.ui.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import com.neillon.authentication.R
import kotlinx.android.synthetic.main.custom_next_button.view.*

class NextButton(context: Context, var attrs: AttributeSet) : ConstraintLayout(context, attrs) {

    private var title: String
    private var isLoading: Boolean = false

    private val textViewTitle: TextView by lazy { findViewById<TextView>(R.id.buttonTitleNextButton) }
    private val imageViewNext: ImageView by lazy { findViewById<ImageView>(R.id.imageViewNextButton) }
    private val progressBarLoading: ProgressBar by lazy { findViewById<ProgressBar>(R.id.progressBarNextButton) }

    init {
        val inflater = (context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater)
        inflater.inflate(R.layout.custom_next_button, this@NextButton, true)

        context.theme.obtainStyledAttributes(attrs, R.styleable.NextButton, 0, 0)
            .apply {
                try {
                    title = getString(R.styleable.NextButton_title).toString()
                    isLoading = getBoolean(R.styleable.NextButton_isLoading, false)
                } finally {
                    recycle()
                }
            }

        textViewTitle.text = title
        background = if (isEnabled) {
            getDrawable(context, R.drawable.background_next_button)
        } else {
            getDrawable(context, R.drawable.background_next_button_disabled)
        }
        if (isLoading) {
            imageViewNextButton.isVisible = false
            progressBarLoading.isVisible = true
        } else {
            imageViewNextButton.isVisible = true
            progressBarLoading.isVisible = false
        }
    }

    fun disable() {
        background = getDrawable(context, R.drawable.background_next_button_disabled)
        isEnabled = false
    }

    fun enable() {
        background = getDrawable(context, R.drawable.background_next_button)
        isEnabled = true
    }

    fun startLoading() {
        background = getDrawable(context, R.drawable.background_next_button)
        isEnabled = false
        isLoading = true

        imageViewNextButton.isVisible = false
        progressBarLoading.isVisible = true
    }

    fun stopLoading() {
        background = getDrawable(context, R.drawable.background_next_button)
        isEnabled = true
        isLoading = false

        imageViewNextButton.isVisible = true
        progressBarLoading.isVisible = false
    }
}