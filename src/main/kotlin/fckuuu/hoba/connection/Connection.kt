/*
 * Hoba - Modern Messenger written in kotlin.
 *
 * Licensed under Apache License 2.0.
 */

package fckuuu.hoba.connection

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.google.gson.JsonPrimitive
import java.io.DataInputStream
import java.io.DataOutputStream
import java.net.Socket

class Connection(server: Socket) {
    private val messages = hashMapOf<String, String>()
    private val user = System.getProperty("user.name")
    private val input = server.getInputStream() as DataInputStream
    private val output = server.getOutputStream() as DataOutputStream

    fun auth(key: Int): Boolean {
        val gson = Gson()

        val jsonObject = JsonObject()

        jsonObject.add("name", JsonPrimitive(System.getProperty("user.name")))
        jsonObject.add("key", JsonPrimitive(key))

        output.writeUTF("hoba:auth ${gson.toJson(JsonParser().parse(jsonObject.toString()))}")

        return input.readUTF().equals("hoba:success", false)
    }

    fun sendMessage(message: String) {
        output.writeUTF("hoba:message $user $message").also { messages[user] = message }
    }

    fun getMessages(): HashMap<String, String> {
        return messages
    }
}