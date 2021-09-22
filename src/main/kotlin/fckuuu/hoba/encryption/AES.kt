/*
 * Copyright 2021 Hoba Development
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
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