/*
 * Copyright 2021 Hoba Development
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 */

package fckuuu.hoba.server.command.commands

import fckuuu.hoba.server.HobaServer
import fckuuu.hoba.server.command.AbstractCommand
import java.io.DataOutputStream
import java.io.ObjectOutputStream
import java.net.Socket
import javax.crypto.SecretKey

class AuthCommand(
    private val parent: HobaServer,
    socket: Socket,
    private val authorizationKey: Int,
    private val userKey: Int,
    private val key: SecretKey
) : AbstractCommand(socket) {
    override val name = "hoba:auth"
    var success = false

    override fun execute() {
        if (authorizationKey == userKey) {
            val output = socket.getOutputStream() as DataOutputStream
            val objectOutput = socket.getOutputStream() as ObjectOutputStream

            output.writeUTF("hoba:success")
            objectOutput.writeObject(key)
            objectOutput.writeObject(parent.jsonManagement.getMessages())

            success = true
        }
    }
}