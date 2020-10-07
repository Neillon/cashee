package com.neillon.cashee.authentication.ui.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.constraintlayout.widget.ConstraintLayout
import com.neillon.authentication.R

class NextButton(context: Context, var attrs: AttributeSet) : ConstraintLayout(context, attrs) {

    private lateinit var title: String
    private var enableButton: Boolean

    private val textViewTitle: TextView by lazy { findViewById<TextView>(R.id.buttonTitleNextButton) }

    init {
        val inflater = (context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater)
        inflater.inflate(R.layout.custom_next_button, this@NextButton, true)

        context.theme.obtainStyledAttributes(attrs, R.styleable.NextButton, 0, 0)
            .apply {
                try {
                    enableButton = getBoolean(R.styleable.NextButton_enable, false)
                    title = getString(R.styleable.NextButton_title).toString()
                } finally {
                    recycle()
                }
            }

        textViewTitle.text = title
        background = if (enableButton) {
            getDrawable(context, R.drawable.background_next_button)
        } else {
            getDrawable(context, R.drawable.background_next_button_disabled)
        }

    }
}