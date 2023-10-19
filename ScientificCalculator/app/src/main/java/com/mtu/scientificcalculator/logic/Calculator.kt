package com.mtu.scientificcalculator.logic

import java.util.Stack

class Calculator {
    fun evaluateMathExpression(expression: String): Double {
        val operators = "+-*/"
        val precedence = mapOf('+' to 1, '-' to 1, '*' to 2, '/' to 2)

        fun isOperator(c: Char) = c in operators
        fun getPrecedence(c: Char) = precedence[c] ?: 0

        fun applyOperator(operators: Stack<Char>, values: Stack<Double>) {
            val b = values.pop()
            val a = values.pop()
            val operator = operators.pop()

            when (operator) {
                '+' -> values.push(a + b)
                '-' -> values.push(a - b)
                '*' -> values.push(a * b)
                '/' -> values.push(a / b)
            }
        }

        val operatorStack = Stack<Char>()
        val valueStack = Stack<Double>()

        var i = 0
        while (i < expression.length) {
            val c = expression[i]
            if (c in '0'..'9' || (c == '.' && i + 1 < expression.length && expression[i + 1] in '0'..'9')) {
                // Parse the number
                val start = i
                while (i < expression.length && (expression[i] in '0'..'9' || expression[i] == '.')) {
                    i++
                }
                val number = expression.substring(start, i).toDouble()
                valueStack.push(number)
            } else if (isOperator(c)) {
                // Process operators
                while (operatorStack.isNotEmpty()  && getPrecedence(operatorStack.peek()) >= getPrecedence(c)) {
                    applyOperator(operatorStack, valueStack)
                }
                operatorStack.push(c)
                i++
            } else if (c == '(') {
                operatorStack.push(c)
                i++
            } else if (c == ')') {
                while (operatorStack.isNotEmpty() && operatorStack.peek() != '(') {
                    applyOperator(operatorStack, valueStack)
                }
                operatorStack.pop()
                i++
            } else {
                // Ignore other characters
                i++
            }
        }

        while (operatorStack.isNotEmpty()) {
            applyOperator(operatorStack, valueStack)
        }
        return valueStack.pop()
    }
}