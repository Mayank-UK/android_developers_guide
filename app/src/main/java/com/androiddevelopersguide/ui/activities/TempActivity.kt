package com.androiddevelopersguide.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.androiddevelopersguide.R

class TempActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_temp)
    }
}