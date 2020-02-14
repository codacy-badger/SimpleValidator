package br.com.validators.simple.message

import br.com.validators.simple.enums.Severity

interface Message {
    val category: String
    val message: String
    val severity: Severity

    fun toShowString(): String
}