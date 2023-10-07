package com.mtu.diceroller.model

class Dice (private val numSides: Int = 6) {
    fun roll(): Int {
        return (1 .. numSides).random()
    }
}