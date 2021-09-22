/*
 * Hoba - Modern Messenger written in kotlin.
 *
 * Licensed under Apache License 2.0.
 */

package fckuuu.hoba.server

import fckuuu.hoba.encryption.AES
import fckuuu.hoba.server.command.commands.AuthCommand
import java.io.DataInputStream
import java.io.DataOutputStream
import java.net.ServerSocket
import javax.crypto.KeyGenerator

class HobaServer(private val authorizationKey: Int, port: Int) {
    private val management = ServerManagement()
    private val server = ServerSocket(port)
    private val key = KeyGenerator.getInstance("AES/CBC/PKCS5Padding")
    private val aes = AES()

    init {
        while (!server.isClosed) {
            Thread {
                val client = server.accept()

                val input = client.getInputStream() as DataInputStream
                val output = client.getOutputStream() as DataOutputStream

                val authorized = false

                while (!client.isClosed) {
                    val command = input.readUTF()

                    if (!authorized) {
                        if (!command.startsWith("hoba:auth")) {
                            output.writeUTF("hoba:error \"User is not authorised.\"")
                        } else {
                            AuthCommand(authorizationKey, Integer.parseInt(command.split(" ")[0])).execute()
                        }
                    } else {
                        management.onCommand(command)
                    }
                }
            }.start()
        }
    }
}
