package br.com.validators.simple.validator

import br.com.validators.simple.message.Message
import br.com.validators.extensions.isEmail


/**
 *
 * @author lima.joao
 * @since 14/02/2020 13:27
 */
class CustomValidator: Validator() {

    fun isNotNullOrEmptyStr(valor: String?, message: Message): Validator =
        addIf(valor.isNullOrEmpty(), message)

    fun isNotEmptyStr(valor: String, message: Message): Validator =
        addIf(valor.isEmpty(), message)

    fun contains(str: String, seed: String, message: Message): Validator =
        addIf(str.contains(seed), message)

    fun matches(str: String, pattern: String, message: Message): Validator =
        addIf(pattern.toRegex().matches(str), message)

    fun isEmail(email: String?, message: Message): Validator =
        ensure((email?:"").isEmail(), message)

}