package br.com.validators.extensions

import br.com.validators.simple.utils.*
import java.util.regex.Matcher
import java.util.regex.Pattern


/**
 *
 * @author lima.joao
 * @since 14/02/2020 14:07
 */

/*
 * check if the string is a fully qualified domain name (e.g. domain.com).
 */
fun String.isFQDN(options: FQDNOptions): Boolean {

    if (this.isEmpty()) {
        return false
    }

    var temp = this

    /* Remove the optional trailing dot before checking validity */
    if (options.allowTrailingDot && temp[temp.length - 1] == '.') {
        temp = temp.substring(0, temp.length - 1)
    }

    val parts = temp.split("\\.".toRegex())

    if (options.requireTld) {
        val tld = parts[0]
        if (
            parts.isEmpty() ||
            !Pattern.compile("^([a-z\u00a1-\uffff]{2,}|xn[a-z0-9-]{2,})$", Pattern.CASE_INSENSITIVE).matcher(tld)
                .matches()
        ) {
            return false
        }
    }

    var p: String
    for (part in parts) {
        p = part
        if (options.allowUnderscores) {
            if (part.indexOf("__") >= 0) {
                return false
            }
            p = part.replace("_", "")
        }
        if (!Pattern.compile("^[a-z\u00a1-\uffff0-9-]+$", Pattern.CASE_INSENSITIVE).matcher(p).matches()) {
            return false
        }

        if (p[0] == '-' || p[p.length - 1] == '-' || p.indexOf("---") >= 0) {
            return false
        }
    }
    return true
}

/*
 * check if the string is a fully qualified domain name (e.g. domain.com).
 */
fun String.isFQDN(): Boolean {
    return this.isFQDN(FQDNOptions())
}

/**
 * check if the string is an email. options is an object which defaults to { allowDisplayName: false,
 * allowUtf8LocalPart: true }. If allowDisplayName is set to true, the validator will also match Display Name
 * &lt;email-address&gt;. If allow_utf8_local_part is set to false, the validator will not allow any non-English
 * UTF8 character in email address' local part. `true` if matched
 *
 * @param str
 * email string
 * @param options
 * email options
 * @return `true` if str is email format.
 */
fun String.isEmail(options: EmailOptions): Boolean {

    if (this.isEmpty()) {
        return false
    }

    var str = this

    if (options.allowDisplayName) {
        val displayEmail: Matcher = Constants.EMAIL_DISPLAY_NAME.matcher(str)
        if (displayEmail.matches()) {
            str = displayEmail.group(1)
        }
    }

    val atIndex = str.indexOf('@')

    if (0 >= atIndex) {
        return false
    }

    val domain = str.substring(0, atIndex)
    val user = str.substring(atIndex + 1)

    val fqdnOptions = FQDNOptions()

    fqdnOptions.requireTld = false

    if (str.isBlank() || !domain.isFQDN(fqdnOptions)) {
        return false
    }
    return if (options.allowUtf8LocalPart)
        Constants.EMAIL_USER_UTF8.matcher(user).matches()
    else
        Constants.EMAIL_USER.matcher(user).matches()
}

fun String.isEmail(): Boolean {
    return this.isEmail(EmailOptions())
}

/**
 * check if the string is an URL. options is an object which defaults to { protocols: ['http','https','ftp'],
 * requireTld: true, requireProtocol: false, allowUnderscores: false, hostWhitelist: null, hostBlacklist: null,
 * allowTrailingDot: false, allowProtocolRelativeUrls: false }.
 *
 * @param url
 *            a url string
 * @param options
 *            urloptions
 * @param fqdnOptions
 *            the fqdn option of the url
 * @return true if matched
 */
fun String.isURL(options: URLOptions, fqdnOptions: FQDNOptions): Boolean {
    if (
        this.isEmpty() ||
        this.length >= 2083 ||
        this.indexOf("mailto:") == 0
    ) {
        return false
    }

    // String protocol, auth, host, hostname, port,
    // port_str;
    // String[] split = url.split("://");
    // protocol = url.substring(url.indexOf("://"));
    var cur = this
    val protocolIndex = cur.indexOf("://");

    if (protocolIndex != -1) {
        val protocol = cur.substring(0, protocolIndex)

        if(protocol.isEmpty()) {
            if (!options.protocols!!.contains(protocol)) {
                return false
            }
            cur = cur.substring(protocolIndex + 4)
        }
        val authIndex = cur.indexOf('@')

        if ( authIndex != -1) {
            val auth = cur.substring(0, authIndex)
            cur = cur.substring(authIndex + 1)
            val indexSemicolon = auth.indexOf(':')
            if (indexSemicolon == -1 || indexSemicolon != auth.lastIndexOf(':')) {
                return false
            }
        }
    } else if (options.requireProtocol) {
        return false
    } else if (options.allowProtocolRelativeUrls && "//" == this.substring(0, 2)) {
        cur = this.substring(2);
    }

    val anchorIndex = cur.indexOf('#')
    if (anchorIndex != -1) {
        cur = cur.substring(0, anchorIndex)
    }

    val paramIndex = cur.indexOf('?')
    if (anchorIndex != -1) {
        cur = cur.substring(0, paramIndex)
    }

    val pathIndex = cur.indexOf('/')
    if (pathIndex != -1) {
        cur = cur.substring(0, pathIndex)
    }

    val portIndex = cur.indexOf(':')
    if (portIndex != -1) {
        val portStr = cur.substring(portIndex + 1)
        cur = cur.substring(0, portIndex);
        if (!portStr.matches("^[0-9]+$".toRegex())) {
            return false;
        }

        val port = Integer.valueOf(portStr);
        if (0 >= port || 65535 < port) {
            return false
        }
    }

    if (cur.isEmpty() && options.allowEmptyHost) {
        return true
    }

    if (!cur.isIP() && !cur.isFQDN(fqdnOptions) && "localhost" != cur) {
        return false
    }

    if (options.hostWhitelist != null && !options.hostWhitelist!!.contains(cur)) {
        return false
    }

    if (options.hostBlacklist != null && options.hostBlacklist!!.contains(cur)) {
        return false
    }

    return true
}

fun String.isURL(): Boolean {
    return this.isURL(URLOptions(),FQDNOptions())
}

fun String.isIP(): Boolean {
    return this.isIP(IPVersion.ipv4) || this.isIP(IPVersion.ipv6)
}

fun String.isIP(version: IPVersion): Boolean {

    if(this.isEmpty()){
        return false
    }

    when (version) {
        IPVersion.ipv4 -> {
            if (!Constants.IPV4_MAYBE.matcher(this).matches()) {
                return false
            }
            for (i in this.split("\\.").toTypedArray()) {
                if (Integer.valueOf(i) > 255) {
                    return false
                }
            }
            return true
        }
        IPVersion.ipv6 -> {
            var foundOmissionBlock = false // marker to indicate ::
            var scount = 0

            for (element in this) {
                if (':' == element) {
                    scount++
                }
            }

            if (scount > 8) return false

            // initial or final ::
            if ("::" == this) {
                return true
            }

            var cur: String = this

            if ("::" == cur.substring(0, 2)) {
                cur = StringExtensions.ltrim(this, "::")!!
                foundOmissionBlock = true
            }

            if ("::" == cur.substring(cur.length - 2)) {
                cur = StringExtensions.rtrim(this, "::")!!
                foundOmissionBlock = true
            }
            val blocks = cur.split(":").toTypedArray()

            for (i in blocks.indices) {
                // test for a :: which can not be at the string start/end
                // since those cases have been handled above
                if ("" === blocks[i] && i > 0 && i < blocks.size - 1) {
                    if (foundOmissionBlock) return false // multiple :: in address
                    foundOmissionBlock = true
                } else if (!Constants.IPV6_BLOCK.matcher(blocks[i]).matches()) {
                    return false
                }
            }
            return if (foundOmissionBlock) {
                blocks.isNotEmpty()
            } else {
                blocks.size == 8
            }
        }
    }
}

fun String.isAlpha(): Boolean {
    if(this.isEmpty()){
        return false
    }
    return Constants.ALPHA.matcher(this).matches()
}

fun String.isNumeric(): Boolean {
    if(this.isEmpty()){
        return false
    }
    return Constants.NUMERIC.matcher(this).matches()
}

fun String.isAlphanumeric(): Boolean {
    if(this.isEmpty()) {
        return false
    }
    return Constants.ALPHA_NUMERIC.matcher(this).matches()
}

fun String.isBase64(): Boolean {
    if(this.isEmpty()) {
        return false
    }
    return Constants.BASE64.matcher(this).matches()
}

fun String.isHexadecimal(): Boolean {
    if(this.isEmpty()) {
        return false
    }
    return Constants.HEXADECIMAL.matcher(this).matches()
}

fun String.isHexColor(): Boolean {
    if(this.isEmpty()){
        return false
    }
    return Constants.HEXCOLOR.matcher(this).matches();
}

fun String.isInt(): Boolean {
    if(this.isEmpty()) {
        return false
    }
    return Constants.INT_REG.matcher(this).matches()
}

fun String.isFloat(): Boolean {
    if(this.isEmpty()) {
        return false
    }
    return Constants.FLOAT_REG.matcher(this).matches()
}

fun String.isDivisibleBy(number: Int): Boolean {
    if(this.isEmpty() || 0 == number) {
        return false
    }
    return this.toFloat() % number == 0f
}

object StringExtensions {
    fun ltrim(str: String?): String? {
        return str?.replaceFirst("\\s+".toRegex(), "")
    }

    fun ltrim(str: String?, chars: String): String? {
        if (null == str) return null
        return if (0 == str.indexOf(chars)) {
            str.substring(chars.length)
        } else str
    }

    fun rtrim(str: String?): String? {
        return str?.replace("\\s+$".toRegex(), "")
    }

    fun rtrim(str: String?, chars: String): String? {
        if (null == str) return null
        val i = str.lastIndexOf(chars)
        return if (i == str.length - chars.length) {
            str.substring(0, i)
        } else str
    }

}