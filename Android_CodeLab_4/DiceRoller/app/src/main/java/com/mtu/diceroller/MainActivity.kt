package com.mtu.diceroller

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mtu.diceroller.databinding.MainLayoutBinding
import com.mtu.diceroller.model.Dice

class MainActivity : AppCompatActivity() {
    // the binding class generated with name = "LayoutName + Binding"
    private lateinit var mainLayoutBinding: MainLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainLayoutBinding = MainLayoutBinding.inflate(layoutInflater)
        val view = mainLayoutBinding.root
//        binding.tv1.text = "Hello Ngo Minh Tu"
//        binding.bt1.text = "Roll"
        val btn: Button = mainLayoutBinding.bt1
//        val tv: TextView = mainLayoutBinding.tv1
        val iv: ImageView = mainLayoutBinding.iv1
        iv.setImageResource(R.drawable.dice_1)
        btn.setOnClickListener {
//            tv.text = rolled.toString()
            when(rollDice()){
                1 -> iv.setImageResource(R.drawable.dice_1)
                2 -> iv.setImageResource(R.drawable.dice_2)
                3 -> iv.setImageResource(R.drawable.dice_3)
                4 -> iv.setImageResource(R.drawable.dice_4)
                5 -> iv.setImageResource(R.drawable.dice_5)
                6 -> iv.setImageResource(R.drawable.dice_6)
            }
            val toast = Toast.makeText(this, "Dice Rolled!", Toast.LENGTH_SHORT)
            toast.show()
        }
        setContentView(view)
    }

    private fun rollDice(): Int{
        return Dice(6).roll()
    }
}
