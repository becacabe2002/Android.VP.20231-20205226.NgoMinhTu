package com.mtu.scientificcalculator

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mtu.scientificcalculator.databinding.MainLayoutBinding

import com.mtu.scientificcalculator.logic.Calculator
import kotlin.math.pow
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {
    private lateinit var mainLayout: MainLayoutBinding
    val cal = Calculator()
    var openBrac = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainLayout = MainLayoutBinding.inflate(layoutInflater)
        val view = mainLayout.root

        var tvSec: TextView = mainLayout.secondTv // for display previous equation
        var tvPrim: TextView = mainLayout.primaryTv // for display current equation or res
        var btnAc: Button = mainLayout.btnAc
        var btnC: Button = mainLayout.btnC
        var btnOpenBrac: Button = mainLayout.btnOpBrac // open bracket
        var btnClsBrac: Button = mainLayout.btnClsBrac // close bracket
        var btnSq: Button = mainLayout.btnSq
        var btnSqrt: Button = mainLayout.btnSqrt
        var btnInv: Button = mainLayout.btnInv // inverse

        var btnMultiple: Button = mainLayout.btnMultiple
        var btnDiv: Button = mainLayout.btnDiv
        var btnPlus: Button = mainLayout.btnPlus
        var btnMinus: Button = mainLayout.btnMinus
        var btnEqual: Button = mainLayout.btnEqual

        var btn0: Button = mainLayout.btn0
        var btn1: Button = mainLayout.btn1
        var btn2: Button = mainLayout.btn2
        var btn3: Button = mainLayout.btn3
        var btn4: Button = mainLayout.btn4
        var btn5: Button = mainLayout.btn5
        var btn6: Button = mainLayout.btn6
        var btn7: Button = mainLayout.btn7
        var btn8: Button = mainLayout.btn8
        var btn9: Button = mainLayout.btn9
        var btnPi: Button = mainLayout.btnPi
        var btnDot: Button = mainLayout.btnDot

        // append character to primary textView
        btn0.setOnClickListener {
            val strTemp = tvPrim.text.toString() + "0"
            tvPrim.text = strTemp
        }

        btn1.setOnClickListener {
            val strTemp = tvPrim.text.toString() + "1"
            tvPrim.text = strTemp
        }

        btn2.setOnClickListener {
            val strTemp = tvPrim.text.toString() + "2"
            tvPrim.text = strTemp
        }

        btn3.setOnClickListener {
            val strTemp = tvPrim.text.toString() + "3"
            tvPrim.text = strTemp
        }

        btn4.setOnClickListener {
            val strTemp = tvPrim.text.toString() + "4"
            tvPrim.text = strTemp
        }

        btn5.setOnClickListener {
            val strTemp = tvPrim.text.toString() + "5"
            tvPrim.text = strTemp
        }

        btn6.setOnClickListener {
            val strTemp = tvPrim.text.toString() + "6"
            tvPrim.text = strTemp
        }

        btn7.setOnClickListener {
            val strTemp = tvPrim.text.toString() + "7"
            tvPrim.text = strTemp
        }

        btn8.setOnClickListener {
            val strTemp = tvPrim.text.toString() + "8"
            tvPrim.text = strTemp
        }

        btn9.setOnClickListener {
            val strTemp = tvPrim.text.toString() + "9"
            tvPrim.text = strTemp
        }

        // check if there is dot before add to tv
        btnDot.setOnClickListener {
            val str = tvPrim.text.toString()
            if(str.get(index = str.length - 1) != '.'){
                val strTemp = tvPrim.text.toString() + "."
                tvPrim.text = strTemp
            }
        }

        btnOpenBrac.setOnClickListener {
            val strTemp = tvPrim.text.toString() + "("
            openBrac += 1
            tvPrim.text = strTemp
        }
        btnClsBrac.setOnClickListener {
            if(openBrac > 0){
                val strTemp = tvPrim.text.toString() + ")"
                openBrac -= 1
                tvPrim.text = strTemp
            } else{
                Toast.makeText(this, "There is no open bracket", Toast.LENGTH_SHORT).show()
            }
        }

        btnMultiple.setOnClickListener {
            val str = tvPrim.text.toString()
            if(str.get(index = str.length - 2) != '*'){
                val strTemp = tvPrim.text.toString() + "*"
                tvPrim.text = strTemp
            }
        }
        btnDiv.setOnClickListener {
            val str = tvPrim.text.toString()
            if(str.get(index = str.length - 2) != '/'){
                val strTemp = tvPrim.text.toString() + "/"
                tvPrim.text = strTemp
            }
        }
        btnPlus.setOnClickListener {
            val str = tvPrim.text.toString()
            if(str.get(index = str.length - 2) != '+'){
                val strTemp = tvPrim.text.toString() + "+"
                tvPrim.text = strTemp
            }
        }
        btnMinus.setOnClickListener {
            val str = tvPrim.text.toString()
            if(str.get(index = str.length - 2) != '-'){
                val strTemp = tvPrim.text.toString() + "-"
                tvPrim.text = strTemp
            }
        }


        btnInv.setOnClickListener {
            val dbTemp = tvPrim.text.toString().toDouble()
            val res = 1 / dbTemp
            val strRes = res.toString()
            val strSec = "1/" + tvPrim.text.toString()
            tvPrim.text = strRes
            tvSec.text = strSec
        }

        // clear all textView
        btnAc.setOnClickListener {
            tvPrim.setText("")
            tvSec.setText("")
            openBrac = 0
        }

        // clear the last character in primary text view
        btnC.setOnClickListener {
            var str = tvPrim.text.toString()
            if (str != ""){
                if (str[str.length - 1] == ')') openBrac += 1
                else if (str[str.length - 1] == '(') openBrac -= 1
                str = str.substring(0, str.length - 2)
                tvPrim.text = str
            }
        }

        // add pi to current value
        btnPi.setOnClickListener {
            val str = tvPrim.text.toString() + "π"
            tvPrim.text = str
            tvSec.text = btnPi.text.toString()
        }

        btnSq.setOnClickListener {
            val dbTemp = tvPrim.text.toString().toDoubleOrNull()
            if (dbTemp != null){
                val res = dbTemp.pow(2)
                val strRes = res.toString()
                val strSec = tvPrim.text.toString() + "^2"
                tvPrim.text = strRes
                tvSec.text = strSec
            } else{
                Toast.makeText(this, "Please enter valid number", Toast.LENGTH_SHORT).show()
            }
        }
        btnSqrt.setOnClickListener {
            val dbTemp = tvPrim.text.toString().toDoubleOrNull()
            if (dbTemp != null){
                val res = sqrt(dbTemp)
                val strRes = res.toString()
                val strSec = "√(" + tvPrim.text.toString() + ")"
                tvPrim.text = strRes
                tvSec.text = strSec
            } else{
                Toast.makeText(this, "Please enter valid number", Toast.LENGTH_SHORT).show()
            }
        }

        btnEqual.setOnClickListener {
            val result: Double = cal.evaluateMathExpression(tvPrim.text.toString())
            tvSec.text = tvPrim.text
            tvPrim.text = result.toString()
        }

        setContentView(view)
    }
}
