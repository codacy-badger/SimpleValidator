package br.com.validators.simple.utils

/**
 * EmailOptions defaults to { allowDisplayName: false, allowUtf8LocalPart: true }. If allowDisplayName is set to true,
 * the validator will also match Display Name &lt;email-address&gt;. If allowUtf8LocalPart is set to false, the validator will
 * not allow any non-English UTF8 character in email address' local part.
 */
class EmailOptions(
    var allowDisplayName: Boolean = false,
    var allowUtf8LocalPart: Boolean = true
)