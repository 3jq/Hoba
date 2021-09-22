/*
 * Hoba - Modern Messenger written in kotlin.
 *
 * Licensed under Apache License 2.0.
 */

package fckuuu.hoba.server.command

abstract class AbstractCommand {
    abstract val name: String
    abstract fun execute()
}