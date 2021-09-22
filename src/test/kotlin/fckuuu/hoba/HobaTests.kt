/*
 * Hoba - Modern Messenger written in kotlin.
 *
 * Licensed under Apache License 2.0.
 */

package fckuuu.hoba

import org.junit.jupiter.api.Test
import java.net.Socket

class HobaTests {
    @Test fun connectionTest() {
        Hoba(Socket("127.0.0.1", 1234), 1234).let {
            if (it.successfulConnection()) it.launch() else println("Invalid Key!").also { return }
        }
    }
}