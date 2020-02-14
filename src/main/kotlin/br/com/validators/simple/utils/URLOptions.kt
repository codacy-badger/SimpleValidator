package br.com.validators.simple.utils

import java.util.*

class URLOptions(
    var protocols: MutableSet<String>? = null,
    var requireTld: Boolean = true,
    var requireProtocol: Boolean = false,
    var allowUnderscores: Boolean = false,
    var hostWhitelist: Set<String>? = null,
    var hostBlacklist: Set<String>? = null,
    var allowTrailingDot: Boolean = false,
    var allowProtocolRelativeUrls: Boolean = false,
    var allowEmptyHost: Boolean = false
    ) {
    init{
        protocols = HashSet<String>()
        (protocols as HashSet<String>).add("http")
        (protocols as HashSet<String>).add("https")
        (protocols as HashSet<String>).add("ftp")
    }
}