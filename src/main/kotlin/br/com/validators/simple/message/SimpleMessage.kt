package br.com.validators.simple.message

import br.com.validators.simple.enums.Severity
import java.util.*

class SimpleMessage(
    override val category: String,
    override val message: String,
    override val severity: Severity,
    vararg messageParameters: String
): Message {

    // Cria um erro
    constructor(message: String, messageParameters: String):this("Erro", message, Severity.ERROR, messageParameters){}

    // Usado quando a message possuir marcações como %s
    private val messageParameters: Array<String>?

    val formattedMessage: String
        get(){
            return if (messageParameters != null && messageParameters.isNotEmpty()) {
                message.format(*messageParameters)
            } else message
        }

    override fun toString(): String {
        return """
            category: $category
            message: $message
            severity: $severity
            parameters: ${messageParameters.toString()}
            """.trimIndent()
    }

    override fun toShowString(): String {
        return "[$category] $formattedMessage"
    }

    override fun hashCode(): Int {
        return (Objects.hashCode(category) xor
                Objects.hashCode(message) xor
                Objects.hash(messageParameters) xor
                Objects.hashCode(severity))
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }
        if (other == null) {
            return false
        }
        if (javaClass != other.javaClass) {
            return false
        }
        val simpleMessage = other as SimpleMessage
        return (category == simpleMessage.category &&
                message == simpleMessage.message && (
                messageParameters == null && simpleMessage.messageParameters == null || Arrays.equals(
                    messageParameters,
                    simpleMessage.messageParameters
                )) &&
                severity == simpleMessage.severity)
    }

    init {
        this.messageParameters = arrayOf(*messageParameters)
    }

    companion object {
        fun error(message: String, vararg messageParameters: String): SimpleMessage {
            return SimpleMessage("Erro", message, Severity.ERROR, *messageParameters)
        }

        fun info(message: String, vararg messageParameters: String): SimpleMessage {
            return SimpleMessage("Info", message, Severity.INFO, *messageParameters)
        }

        fun warn(message: String, vararg messageParameters: String): SimpleMessage {
            return SimpleMessage("Warn", message, Severity.WARN, *messageParameters)
        }
    }

}

