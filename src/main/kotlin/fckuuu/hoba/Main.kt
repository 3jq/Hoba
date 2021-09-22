package fckuuu.hoba

import java.net.Socket
import javax.crypto.KeyGenerator
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.SecretKeySpec

fun main(args: Array<String>) {
    val hoba = Hoba(Socket(args[0], Integer.parseInt(args[1])), KeyGenerator.getInstance("AES/CBC/PKCS5Padding").generateKey())


}