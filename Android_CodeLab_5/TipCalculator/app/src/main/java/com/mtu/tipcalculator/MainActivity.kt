package com.mtu.tipcalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mtu.tipcalculator.databinding.MainLayoutBinding
import com.mtu.tipcalculator.ui.theme.TipCalculatorTheme

class MainActivity : AppCompatActivity() {

    private lateinit var mainLayoutBinding: MainLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainLayoutBinding = MainLayoutBinding.inflate(layoutInflater)
        val view = mainLayoutBinding.root

        setContentView(view)
    }
}
