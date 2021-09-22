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
import java.net.Socket

class MessageCommand(
    private val fullCommand: String,
    private val parent: HobaServer,
    socket: Socket
) : AbstractCommand(socket) {
    override val name = "hoba:message"

    override fun execute() {
        var messagesCount = parent.messages.size
        parent.messages[messagesCount++] = fullCommand.split(" ")[0]
    }
}