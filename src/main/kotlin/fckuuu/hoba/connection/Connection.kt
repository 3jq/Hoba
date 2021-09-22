package fckuuu.hoba.connection

import java.io.DataOutputStream
import java.net.Socket

class Connection(private val server: Socket) {

    fun auth(): Boolean {
        val out = server.getOutputStream() as DataOutputStream

        return true
    }
}