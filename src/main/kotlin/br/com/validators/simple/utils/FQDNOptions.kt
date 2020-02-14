package br.com.validators.simple.utils

/**
 * FQDNOptions defaults to { require_tld: true, allow_underscores: false, allow_trailing_dot: false }.
 *
 */
class FQDNOptions(
    var requireTld: Boolean = true,
    var allowUnderscores: Boolean = false,
    var allowTrailingDot: Boolean = false
)