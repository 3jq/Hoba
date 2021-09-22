/*
 * Copyright 2021 Hoba Development
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 */

package fckuuu.hoba

import com.sun.jna.Platform
import fckuuu.hoba.connection.Connection
import fckuuu.hoba.encryption.AES
import java.net.Socket
import java.util.*

class Hoba(
    private val server: Socket,
    key: Int
) {
    private val connection: Connection
    private val aes = AES()
    private var success = false

    init {
        val scanner = Scanner(System.`in`)
        println("Write your username:")
        val username = scanner.nextLine()
        connection = Connection(username, server)
        connection.let { success = it.auth(key) }
    }

    fun launch() {
        Thread {
            while (!server.isClosed) {
                if (Platform.isWindows()) Runtime.getRuntime().exec("cls") else Runtime.getRuntime().exec("clear")

                println("Successfully connected to server.")
                connection.updateMessages()
                for (message in connection.jsonManagement.getMessages()) {
                    println(aes.decrypt(message.value.toByteArray(), connection.key))
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