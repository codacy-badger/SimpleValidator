package br.com.validators.simple

import br.com.validators.simple.enums.Severity
import br.com.validators.simple.message.Message
import javafx.scene.control.Alert
import java.util.*
import java.util.function.Consumer
import kotlin.collections.ArrayList

open class Validator {

    private val messages: MutableList<Message>

    private var hasError = false
    private var hasInfo = false
    private var hasWarn = false

    private var height = 150
    private var width = 300

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

    val errors: List<String>
        get() {
            return getBySeverity(Severity.ERROR)
        }

    val informations: List<String>
        get() {
            return getBySeverity(Severity.INFO)
        }

    val warnings: List<String>
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

    fun showErrors(block: (erros: String) -> Unit) {
        val exibir = StringBuilder()
        for (msg in errors) {
            exibir
                .append(msg)
                .append("\n")
        }
        block(exibir.toString())
    }

    fun showInformations(block: (informations: String) -> Unit) {
        val exibir = StringBuilder()
        for (msg in informations) {
            exibir
                .append(msg)
                .append("\n")
        }
        block(exibir.toString())
    }

    fun showWarnings(block: (warnings: String) -> Unit) {
        val exibir = StringBuilder()
        for (msg in warnings) {
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