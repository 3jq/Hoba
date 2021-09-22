/*
 * Hoba - Modern Messenger written in kotlin.
 *
 * Licensed under Apache License 2.0.
 */

package fckuuu.hoba

import com.sun.jna.Platform
import fckuuu.hoba.connection.Connection
import java.net.Socket
import java.util.*

class Hoba(private val server: Socket, key: Int) {
    private val connection: Connection = Connection(server)
    private var success = false

    init {
        connection.let { success = it.auth(key) }
    }

    fun launch() {
        Thread {
            while (!server.isClosed) {
                if (Platform.isWindows()) Runtime.getRuntime().exec("cls") else Runtime.getRuntime().exec("clear")

                println("Successfully connected to server.")
                for (message in connection.getMessages()) {
                    println("${message.key}: ${message.value}")
                }

                val scanner = Scanner(System.`in`)
                val message = scanner.nextLine()
                connection.sendMessage(message)
            }
        }.start()
    }

    fun successfulConnection(): Boolean {
        return success
    }
}