package br.com.validators.simple.validator

import br.com.validators.simple.enums.Severity
import br.com.validators.simple.message.Message
import java.util.function.Consumer
import kotlin.collections.ArrayList

open class Validator {

    private val messages: MutableList<Message>

    private var hasError = false
    private var hasInfo = false
    private var hasWarn = false

    private fun check(expresison: Boolean, message: Message): Validator {
        if (expresison) {
            messages.add(message)
        }
        return this
    }

    private fun verify() {
        hasError = false
        hasInfo = false
        hasWarn = false
        messages.forEach(Consumer { message: Message ->
            when (message.severity) {
                Severity.ERROR -> {
                    hasError = true
                    hasInfo = true
                    hasWarn = true
                }
                Severity.INFO -> {
                    hasInfo = true
                    hasWarn = true
                }
                Severity.WARN -> hasWarn = true
            }
        })
    }

    private val errors: List<String>
        get() {
            return getBySeverity(Severity.ERROR)
        }

    private val informations: List<String>
        get() {
            return getBySeverity(Severity.INFO)
        }

    private val warnings: List<String>
        get() {
            return getBySeverity(Severity.WARN)
        }

    private fun getBySeverity(severity: Severity): MutableList<String>{
        val msgs: MutableList<String> = ArrayList()
        messages.forEach(Consumer { msg: Message ->
            if (msg.severity === severity) {
                msgs.add("[%s] %s".format(msg.severity, msg.message))
            }
        })
        return msgs

    }

    fun hasError(): Boolean {
        verify()
        return hasError
    }

    fun hasInfo(): Boolean {
        verify()
        return hasInfo
    }

    fun hasWarn(): Boolean {
        verify()
        return hasWarn
    }

    /**
     * Insert one **Message** when expression is true
     */
    fun addIf(expression: Boolean, message: Message): Validator {
        return check(expression, message)
    }

    /**
     * Insert one **Message** when expression is false
     */
    fun ensure(expression: Boolean?, message: Message): Validator {
        return check(!expression!!, message)
    }

    @Deprecated(message="Usar o metodo execWhen")
    fun showErrors(block: (erros: String) -> Unit) {
        execWhen(Severity.ERROR, block)
    }

    @Deprecated(message="Usar o metodo execWhen")
    fun showInformations(block: (informations: String) -> Unit) {
        execWhen(Severity.INFO, block)
    }

    @Deprecated(message="Usar o metodo execWhen")
    fun showWarnings(block: (warnings: String) -> Unit) {
        execWhen(Severity.WARN, block)
    }

    fun execWhen(severity: Severity, block:(messages: String) -> Unit) {
        val exibir = StringBuilder()
        val lista = when(severity){
            Severity.ERROR -> errors
            Severity.WARN -> warnings
            Severity.INFO -> informations
        }
        for (msg in lista) {
            exibir
                .append(msg)
                .append("\n")
        }
        block(exibir.toString())
    }

    fun execWhenNoMsgs(block: () -> Unit): Validator {
        verify()
        if(!hasError && !hasInfo && !hasWarn){
            block()
        }
        return this
    }

    init {
        messages = ArrayList()
    }
}