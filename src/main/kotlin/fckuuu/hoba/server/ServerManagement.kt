/*
 * Hoba - Modern Messenger written in kotlin.
 *
 * Licensed under Apache License 2.0.
 */

package fckuuu.hoba.server

import fckuuu.hoba.server.command.AbstractCommand
import org.reflections.Reflections
import java.util.function.Consumer

class ServerManagement {
    private val commands = arrayListOf<AbstractCommand>()

    init {
        val reflections: Set<Class<out AbstractCommand?>> = Reflections("rappo.client.module.modules").getSubTypesOf(AbstractCommand::class.java)
        reflections.forEach(Consumer { clazz: Class<out AbstractCommand?> ->
            try {
                commands.add(clazz.newInstance()!!)
            } catch (t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    fun onCommand(command: String) {

    }
}