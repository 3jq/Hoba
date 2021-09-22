/*
 * Copyright 2021 Hoba Development
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 */

package fckuuu.hoba.server

import fckuuu.hoba.server.command.commands.AuthCommand
import fckuuu.hoba.server.command.commands.MessageCommand
import java.io.DataInputStream
import java.io.DataOutputStream
import java.net.ServerSocket
import javax.crypto.KeyGenerator

class HobaServer(
    port: Int,
    private val authorizationKey: Int
) {
    val users = arrayListOf<String>()
    val messages = hashMapOf<Int, String>()
    private val server = ServerSocket(port)
    private val key = KeyGenerator.getInstance("AES/CBC/PKCS5Padding").generateKey()

    init {
        while (!server.isClosed) {
            Thread {
                val client = server.accept()

                val input = client.getInputStream() as DataInputStream
                val output = client.getOutputStream() as DataOutputStream

                var authorized = false

                while (!client.isClosed) {
                    val command = input.readUTF()

                    if (!authorized) {
                        if (!command.startsWith("hoba:auth")) {
                            output.writeUTF("hoba:error \"User is not authorised.\"")
                        } else {
                            AuthCommand(
                                client,
                                authorizationKey,
                                Integer.parseInt(command.split(" ")[0]),
                                key
                            ).let {
                                it.execute()
                                authorized = it.success
                            }
                        }
                    } else {
                        MessageCommand(command, this, client).execute()
                    }
                }
            }.start()
        }
    }
}
