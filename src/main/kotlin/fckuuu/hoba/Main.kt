/*
 * Copyright 2021 Hoba Development
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 */

package fckuuu.hoba

import fckuuu.hoba.server.HobaServer
import java.net.Socket

fun main(args: Array<String>) {
    if (args.isEmpty()) {
        println("Hoba - Modern Messenger written in kotlin.")
        println("Commands:")
        println("- \"hostserver {(Integer) Port} {(Integer) Authorization Key}\" - Hosts a server for Hoba, so other people can connect to you.")
        println("- \"connect {IP} {(Integer) Port} {(Integer) Key}\"")
        return
    }

    when (args.size) {
        4 -> {
            Hoba(Socket(args[1], Integer.parseInt(args[2])), Integer.parseInt(args[3])).let {
                if (it.successfulConnection()) it.launch() else println("Invalid Key!").also { return }
            }
        }

        3 -> {
            HobaServer(Integer.parseInt(args[1]), Integer.parseInt(args[2]))
        }
    }
}