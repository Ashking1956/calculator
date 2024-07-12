package com.prodigyinternship.calculatorapp

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class MainViewModel : ViewModel() {
    private var _result = MutableStateFlow(0.0)
    val result: StateFlow<Double> get() = _result

    private var _currentInput = MutableStateFlow("")
    val currentInput: StateFlow<String> get() = _currentInput

    private var currentOperator: String? = null
    private var storedValue: Double? = null
    val _storedValue: Double? get() = storedValue

    private var resetInput = false

    fun onNumberClick(number: String) {
        if (resetInput) {
            _currentInput.value = ""
            resetInput = false
        }
        _currentInput.value += number
    }

    fun onOperatorClick(operator: String) {
        if (_currentInput.value.isNotEmpty()) {
            if (storedValue == null) {
                storedValue = _currentInput.value.toDoubleOrNull() ?: 0.0
            } else {
                onEqualClick()
                storedValue = _result.value
            }
            _currentInput.value = ""
            currentOperator = operator
        }
    }

    fun onEqualClick() {
        if (storedValue != null && _currentInput.value.isNotEmpty()) {
            val currentValue = _currentInput.value.toDoubleOrNull() ?: 0.0
            _result.value = when (currentOperator) {
                "+" -> storedValue!! + currentValue
                "-" -> storedValue!! - currentValue
                "*" -> storedValue!! * currentValue
                "/" -> {
                    if (currentValue != 0.0) {
                        storedValue!! / currentValue
                    } else {
                        Double.NaN // Handle division by zero
                    }
                }

                "%" -> storedValue!! * currentValue / 100
                else -> _result.value
            }
            _currentInput.value = _result.value.toString()
            storedValue = null
            currentOperator = null
            resetInput = true
        }
    }

    fun onClearClick() {
        _result.value = 0.0
        _currentInput.value = ""
        storedValue = null
        currentOperator = null
        resetInput = false
    }

    fun onDeleteClick() {
        if (_currentInput.value.isNotEmpty()) {
            _currentInput.value = _currentInput.value.dropLast(1)
        }
    }

    fun onToggleSignClick() {
        if (_currentInput.value.isNotEmpty()) {
            if (_currentInput.value.startsWith("-")) {
                _currentInput.value = _currentInput.value.drop(1)
            } else {
                _currentInput.value = "-${_currentInput.value}"
            }
        }
    }

    fun onDecimalClick() {
        if (!_currentInput.value.contains(".")) {
            _currentInput.value += "."
        }
    }
}

