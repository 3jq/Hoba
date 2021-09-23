/*
 * Copyright 2021 Hoba Development
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 */

package fckuuu.hoba.json

import com.google.gson.Gson
import com.google.gson.JsonParser
import java.io.*
import java.nio.file.Files

class JsonManagement(val type: String) {
    private val gson = Gson()

    fun saveUser(user: String) {
        val path = File("$type.json").toPath()
        if (!path.toFile().exists() && !path.toFile().mkdir()) return

        val rawJson = loadFile(path.toFile())
        val jsonObject = JsonParser().parse(rawJson).asJsonObject
        if (jsonObject["users"] != null) {
            val userObject = jsonObject["users"].asJsonArray
            userObject.add(user)
            jsonObject.add("users", userObject)

            Files.write(path, gson.toJson(JsonParser().parse(jsonObject.toString())).toByteArray())
        }
    }

    fun saveMessage(message: String) {
        val path = File("$type.json").toPath()
        if (!path.toFile().exists() && !path.toFile().mkdir()) return

        val rawJson = loadFile(path.toFile())
        val jsonObject = JsonParser().parse(rawJson).asJsonObject
        if (jsonObject["messages"] != null) {
            val messageObject = jsonObject["messages"].asJsonArray
            messageObject.add(message)
            jsonObject.add("users", messageObject)

            Files.write(path, gson.toJson(JsonParser().parse(jsonObject.toString())).toByteArray())
        }
    }

    fun saveMessages(messages: HashMap<Int, String>) {
        val path = File("$type.json").toPath()
        if (!path.toFile().exists() && !path.toFile().mkdir()) return

        val rawJson = loadFile(path.toFile())
        val jsonObject = JsonParser().parse(rawJson).asJsonObject
        if (jsonObject["messages"] != null) {
            val messageObject = jsonObject["messages"].asJsonArray
            messages.forEach { messageObject.add(it.value) }
            jsonObject.add("users", messageObject)

            Files.write(path, gson.toJson(JsonParser().parse(jsonObject.toString())).toByteArray())
        }
    }

    fun getUsers(): ArrayList<String> {
        val users = arrayListOf<String>()

        val path = File("$type.json").toPath()
        if (!path.toFile().exists() && !path.toFile().mkdir()) return users

        val rawJson = loadFile(path.toFile())
        val jsonObject = JsonParser().parse(rawJson).asJsonObject

        if (jsonObject["users"] != null) {
            val userObject = jsonObject["users"].asJsonArray

            userObject.forEach { user ->
                run {
                    users.add(user.toString())
                }
            }
        }

        return users
    }

    fun getMessages(): HashMap<Int, String> {
        val messages = hashMapOf<Int, String>()

        val path = File("$type.json").toPath()
        if (!path.toFile().exists() && !path.toFile().mkdir()) return messages

        val rawJson = loadFile(path.toFile())
        val jsonObject = JsonParser().parse(rawJson).asJsonObject

        if (jsonObject["messages"] != null) {
            val messageObject = jsonObject["messages"].asJsonArray

            var count = 0
            messageObject.forEach { message ->
                run {
                    messages[count] = message.toString()
                    count++
                }
            }
        }

        return messages
    }

    private fun loadFile(file: File): String? {
        val stream = FileInputStream(file.absolutePath)
        val resultStringBuilder = StringBuilder()
        BufferedReader(InputStreamReader(stream)).use { br ->
            var line: String?
            while (br.readLine().also { line = it } != null) {
                resultStringBuilder.append(line).append("\n")
            }
        }

        return resultStringBuilder.toString()
    }
}