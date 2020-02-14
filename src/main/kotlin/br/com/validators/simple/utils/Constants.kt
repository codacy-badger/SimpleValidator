package br.com.validators.simple.utils

import java.util.regex.Pattern

/**
 *
 * @author lima.joao
 * @since 14/02/2020 13:50
 */
object Constants {
    val EMAIL_DISPLAY_NAME: Pattern = Pattern
        .compile(
            //"^(?:[a-z]|\\d|[!#\\$%&'\\*\\+\\-\\/=\\?\\^_`{\\|}~\\.]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(?:[a-z]|\\d|[!#\\$%&'\\*\\+\\-\\/=\\?\\^_`{\\|}~\\.]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]|\\s)*<(.+)>$",
            "^(?:[a-z]|\\d|[!#$%&'*+\\-/=?^_`{|}~.]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(?:[a-z]|\\d|[!#$%&'*+\\-/=?^_`{|}~.]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]|\\s)*<(.+)>$",
            Pattern.CASE_INSENSITIVE
        )

    val EMAIL_USER_UTF8: Pattern = Pattern
        .compile(
         //   "^((([a-z]|\\d|[!#\\$%&'\\*\\+\\-\\/=\\?\\^_`{\\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\\.([a-z]|\\d|[!#\\$%&'\\*\\+\\-\\/=\\?\\^_`{\\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\\x22)((((\\x20|\\x09)*(\\x0d\\x0a))?(\\x20|\\x09)+)?(([\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x7f]|\\x21|[\\x23-\\x5b]|[\\x5d-\\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\\\([\\x01-\\x09\\x0b\\x0c\\x0d-\\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\\x20|\\x09)*(\\x0d\\x0a))?(\\x20|\\x09)+)?(\\x22)))$",
            "^((([a-z]|\\d|[!#$%&'*+\\-/=?^_`{|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\\.([a-z]|\\d|[!#$%&'*+\\-/=?^_`{|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\\x22)(((([\\x20\\x09])*(\\x0d\\x0a))?([\\x20\\x09])+)?(([\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x7f]|\\x21|[\\x23-\\x5b]|[\\x5d-\\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\\\([\\x01-\\x09\\x0b\\x0c\\x0d-\\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*((([\\x20\\x09])*(\\x0d\\x0a))?([\\x20\\x09])+)?(\\x22)))$",
            Pattern.CASE_INSENSITIVE
        )

    val EMAIL_USER: Pattern = Pattern
        .compile(
//            "^((([a-z]|\\d|[!#\\$%&'\\*\\+\\-\\/=\\?\\^_`{\\|}~])+(\\.([a-z]|\\d|[!#\\$%&'\\*\\+\\-\\/=\\?\\^_`{\\|}~])+)*)|((\\x22)((((\\x20|\\x09)*(\\x0d\\x0a))?(\\x20|\\x09)+)?(([\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x7f]|\\x21|[\\x23-\\x5b]|[\\x5d-\\x7e])|(\\\\[\\x01-\\x09\\x0b\\x0c\\x0d-\\x7f])))*(((\\x20|\\x09)*(\\x0d\\x0a))?(\\x20|\\x09)+)?(\\x22)))$",
            "^((([a-z]|\\d|[!#$%&'*+\\-/=?^_`{|}~])+(\\.([a-z]|\\d|[!#$%&'*+\\-/=?^_`{|}~])+)*)|((\\x22)(((([\\x20\\x09])*(\\x0d\\x0a))?([\\x20\\x09])+)?(([\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x7f]|\\x21|[\\x23-\\x5b]|[\\x5d-\\x7e])|(\\\\[\\x01-\\x09\\x0b\\x0c\\x0d-\\x7f])))*((([\\x20\\x09])*(\\x0d\\x0a))?([\\x20\\x09])+)?(\\x22)))$",
            Pattern.CASE_INSENSITIVE
        )

    val IPV4_MAYBE: Pattern = Pattern.compile("^(\\d+)\\.(\\d+)\\.(\\d+)\\.(\\d+)$")

    val IPV6_BLOCK: Pattern = Pattern.compile("^[0-9A-F]{1,4}$", Pattern.CASE_INSENSITIVE)

    val ALPHA: Pattern = Pattern.compile("^[A-Z]+$", Pattern.CASE_INSENSITIVE)

    val ALPHA_NUMERIC: Pattern = Pattern.compile("^^[0-9A-Z]+$", Pattern.CASE_INSENSITIVE)

    val NUMERIC = Pattern.compile("^^[-+]?[0-9]+$")

    val INT_REG = Pattern.compile("^^(?:[-+]?(?:0|[1-9][0-9]*))$")

    val FLOAT_REG: Pattern = Pattern.compile("^^(?:[-+]?(?:[0-9]+))?(?:\\.[0-9]*)?(?:[eE][+\\-]?(?:[0-9]+))?$")

    val HEXADECIMAL:Pattern = Pattern.compile("^^[0-9A-F]+$", Pattern.CASE_INSENSITIVE)

    val HEXCOLOR:Pattern = Pattern.compile("^^#?([0-9A-F]{3}|[0-9A-F]{6})$", Pattern.CASE_INSENSITIVE)

    val ASCII:Pattern = Pattern.compile("^[\\x00-\\x7F]+$")

    val MULTIBYTE:Pattern = Pattern.compile("[^\\x00-\\x7F]")

    val FULL_WIDTH:Pattern = Pattern.compile("[^\u0020-\u007E\uFF61-\uFF9F\uFFA0-\uFFDC\uFFE8-\uFFEE0-9a-zA-Z]")

    val HALF_WIDTH:Pattern = Pattern.compile("[\u0020-\u007E\uFF61-\uFF9F\uFFA0-\uFFDC\uFFE8-\uFFEE0-9a-zA-Z]")

    val SURROGATE_PAIR:Pattern = Pattern.compile("[\uD800-\uDBFF][\uDC00-\uDFFF]")

    val BASE64:Pattern = Pattern.compile("^(?:[A-Z0-9+/]{4})*(?:[A-Z0-9+/]{2}==|[A-Z0-9+/]{3}=|[A-Z0-9+/]{4})$",Pattern.CASE_INSENSITIVE)

}