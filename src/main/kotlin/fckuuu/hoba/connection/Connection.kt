/*
 * Copyright 2021 Hoba Development
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 */

package fckuuu.hoba.connection

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.google.gson.JsonPrimitive
import fckuuu.hoba.encryption.AES
import fckuuu.hoba.json.JsonManagement
import java.io.DataInputStream
import java.io.DataOutputStream
import java.io.ObjectInputStream
import java.net.Socket
import java.text.SimpleDateFormat
import java.util.*
import javax.crypto.SecretKey
import kotlin.collections.HashMap

class Connection(
    private val username: String,
    server: Socket
) {
    val jsonManagement = JsonManagement("client")
    lateinit var key: SecretKey
    private val aes = AES()
    private val input: DataInputStream = server.getInputStream() as DataInputStream
    private val output: DataOutputStream = server.getOutputStream() as DataOutputStream
    private val objectInput: ObjectInputStream = server.getInputStream() as ObjectInputStream

    fun auth(key: Int): Boolean {
        output.writeUTF("hoba:auth $username $key")

        var message = input.readUTF()

        if (message.equals("hoba:success", false)) {
            this.key = objectInput.readObject() as SecretKey
            jsonManagement.saveMessages(objectInput.readObject() as HashMap<Int, String>)
            return true
        } else {
            println(message.split(" ").toString())
        }

        return false
    }

    fun updateMessages() {
        jsonManagement.saveMessages(objectInput.readObject() as HashMap<Int, String>)
    }

    fun sendMessage(message: String) {
        val formatter = SimpleDateFormat("HH:mm:ss")
        val date = Date()
        output.writeUTF("hoba:message ${aes.encrypt("[${formatter.format(date)}] $username: $message".toByteArray(), key)}").also {
            // The thing is that if I'll do "messages[messages.size++]" it will not work, lol.
            var messagesCount = jsonManagement.getMessages().size
            jsonManagement.saveMessage("[${formatter.format(date)}] $username: $message")
        }
    }

    fun getMessages(): HashMap<Int, String> {
        return jsonManagement.getMessages()
    }
}