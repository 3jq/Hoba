/*
 * Hoba - Modern Messenger written in kotlin.
 *
 * Licensed under Apache License 2.0.
 */

package fckuuu.hoba.encryption

import java.util.*
import javax.crypto.Cipher
import javax.crypto.Cipher.DECRYPT_MODE
import javax.crypto.Cipher.ENCRYPT_MODE
import javax.crypto.SecretKey

class AES {
    fun encrypt(data: ByteArray, key: SecretKey): String {
        val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        cipher.init(ENCRYPT_MODE, key)
        return String(Base64.getEncoder().encode(cipher.doFinal(data)))
    }

    fun decrypt(data: ByteArray, key: SecretKey): String {
        val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        cipher.init(DECRYPT_MODE, key)
        return String(cipher.doFinal(Base64.getDecoder().decode(data)))
    }
}