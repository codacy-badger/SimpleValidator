package br.com.validators.simple.message

import br.com.validators.simple.enums.Severity
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

/**
 * @author lima.joao
 * @since 14/02/2020 11:07
 */
internal class SimpleMessageTest {

    @Test
    fun mensagemDeveriaSerFormatadaCorretamenteComUmParametro() {
        val simpleMessage = SimpleMessage(
            "Teste",
            "deveria exibir uma %s",
            Severity.ERROR,
            "mensagem"
        )
        assertEquals("deveria exibir uma mensagem", simpleMessage.formattedMessage)
    }

    @Test
    fun mensagemDeveriaSerFormatadaCorretamenteComDoisParametros() {
        val simpleMessage = SimpleMessage(
            "Teste",
            "deveria exibir uma %s contendo %s parametros",
            Severity.ERROR,
            *arrayOf("mensagem", "2")
        )
        assertEquals("deveria exibir uma mensagem contendo 2 parametros", simpleMessage.formattedMessage)
    }

    @Test
    fun toShowString() {
        val simpleMessage = SimpleMessage(
            "Teste",
            "deveria exibir uma %s contendo %s parametros",
            Severity.ERROR,
            *arrayOf("mensagem", "2")
        )
        assertEquals("[Teste] deveria exibir uma mensagem contendo 2 parametros", simpleMessage.toShowString())
    }

}