/*
 * Hoba - Modern Messenger written in kotlin.
 *
 * Licensed under Apache License 2.0.
 */

package fckuuu.hoba

import java.net.Socket
import javax.crypto.KeyGenerator
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.SecretKeySpec

fun main(args: Array<String>) {
    if (args.isEmpty()) {
        println("Hoba - Modern Messenger written in kotlin.")
        println("Commands:")
        println("- \"hostserver {(Integer) Port}\" - Hosts a server for Hoba, so other people can connect to you.")
        println("- \"connect {IP} {(Integer) Port} {(Integer) Key}\"")
        return
    }

    when (args.size) {
        4 -> {
            Hoba(Socket(args[0], Integer.parseInt(args[1])), Integer.parseInt(args[2])).let {
                if (it.successfulConnection()) it.launch() else println("Invalid Key!").also { return }
            }
        }

        3 -> {
            // TODO: 22.09.2021 Server Host
        }
    }
}