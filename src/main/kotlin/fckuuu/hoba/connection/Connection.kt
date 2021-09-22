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
import java.io.DataInputStream
import java.io.DataOutputStream
import java.io.ObjectInputStream
import java.net.Socket
import java.text.SimpleDateFormat
import java.util.*
import javax.crypto.SecretKey
import kotlin.collections.HashMap

class Connection(server: Socket) {
    private val messages = hashMapOf<Int, String>()
    private val user = System.getProperty("user.name")
    lateinit var key: SecretKey
    private val aes = AES()
    private val input = server.getInputStream() as DataInputStream
    private val output = server.getOutputStream() as DataOutputStream
    private val objectInput = server.getInputStream() as ObjectInputStream

    fun auth(key: Int): Boolean {
        val gson = Gson()

        val jsonObject = JsonObject()

        jsonObject.add("name", JsonPrimitive(System.getProperty("user.name")))
        jsonObject.add("key", JsonPrimitive(key))

        output.writeUTF("hoba:auth ${gson.toJson(JsonParser().parse(jsonObject.toString()))}")

        if (input.readUTF().equals("hoba:success", false)) {
            this.key = objectInput.readObject() as SecretKey
            return true
        }

        return false
    }

    fun sendMessage(message: String) {
        val formatter = SimpleDateFormat("HH:mm:ss")
        val date = Date()
        output.writeUTF("hoba:message ${aes.encrypt("[${formatter.format(date)}] $user: $message".toByteArray(), key)}").also {
            // The thing is that if I'll do "messages[messages.size++]" it will not work, lol.
            var messagesCount = messages.size
            messages[messagesCount++] = "[${formatter.format(date)}] $user: $message"
        }
    }

    fun getMessages(): HashMap<Int, String> {
        return messages
    }
}