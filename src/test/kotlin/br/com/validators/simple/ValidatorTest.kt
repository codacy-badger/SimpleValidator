package br.com.validators.simple

import br.com.validators.simple.enums.Severity
import br.com.validators.simple.message.SimpleMessage
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

/**
 * @author lima.joao
 * @since 14/02/2020 11:41
 */
internal class ValidatorTest {

    @Test
    fun deveriaAdicionarUmErroComAddIf() {
        val validator = Validator()
        validator.addIf(true,
            SimpleMessage(
                "ERRO",
                "TESTE %s",
                Severity.ERROR,
                "ERRO"
            )
        )
        assertTrue(validator.hasError())
    }

    @Test
    fun deveriaAdicionarUmWarnComAddIf() {
        val validator = Validator()
        validator.addIf(true,
            SimpleMessage(
                "WARN",
                "TESTE %s",
                Severity.WARN,
                "WARN"
            )
        )
        assertTrue(validator.hasWarn())
    }

    @Test
    fun deveriaAdicionarUmaInfoComAddIf() {
        val validator = Validator()
        validator.addIf(true,
            SimpleMessage(
                "INFO",
                "TESTE %s",
                Severity.INFO,
                "INFO"
            )
        )
        assertTrue(validator.hasInfo())
    }

    @Test
    fun deveriaAdicionarUmErroComEnsure() {
        val validator = Validator()
        validator.ensure(false,
            SimpleMessage(
                "ERRO",
                "TESTE %s",
                Severity.ERROR,
                "ERRO"
            )
        )
        assertTrue(validator.hasError())
    }

    @Test
    fun deveriaAdicionarUmWarnComEnsure() {
        val validator = Validator()
        validator.ensure(false,
            SimpleMessage(
                "WARN",
                "TESTE %s",
                Severity.WARN,
                "WARN"
            )
        )
        assertTrue(validator.hasWarn())
    }

    @Test
    fun deveriaAdicionarUmaInfoComEnsure() {
        val validator = Validator()
        validator.ensure(false,
            SimpleMessage(
                "INFO",
                "TESTE %s",
                Severity.INFO,
                "INFO"
            )
        )
        assertTrue(validator.hasInfo())
    }

}