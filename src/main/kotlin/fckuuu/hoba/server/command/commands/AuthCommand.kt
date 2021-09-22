/*
 * Hoba - Modern Messenger written in kotlin.
 *
 * Licensed under Apache License 2.0.
 */

package fckuuu.hoba.server.command.commands

import fckuuu.hoba.server.command.AbstractCommand

class AuthCommand(private val authorizationKey: Int, private val userKey: Int) : AbstractCommand() {
    override val name = "hoba:auth"

    override fun execute() {
        if (authorizationKey == userKey) {
            // TODO: 22.09.2021 Serializing/Deserializing key
            // TODO: 22.09.2021 Serializing/Deserializing hashmap with messages 
            // TODO: 22.09.2021 Registering user
        }
    }
}