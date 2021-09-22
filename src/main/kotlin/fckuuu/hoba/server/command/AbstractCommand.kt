/*
 * Copyright 2021 Hoba Development
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 */

package fckuuu.hoba.server.command

import java.net.Socket

abstract class AbstractCommand(socket: Socket) {
    protected val socket = socket

    abstract val name: String
    abstract fun execute()
}