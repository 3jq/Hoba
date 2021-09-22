package fckuuu.hoba

import fckuuu.hoba.connection.Connection
import java.net.Socket

class Hoba(server: Socket, key: Int) {
    val connection: Connection = Connection(server)
    private var success = false

    init {
        connection.let { success = it.auth() }
    }

    fun launch() {

    }

    fun successfulConnection(): Boolean {
        return success
    }
}