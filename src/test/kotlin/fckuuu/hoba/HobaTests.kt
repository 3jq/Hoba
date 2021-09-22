/*
 * Copyright 2021 Hoba Developement
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 */

package fckuuu.hoba

import fckuuu.hoba.server.HobaServer
import org.junit.jupiter.api.Test
import java.net.Socket

class HobaTests {
    @Test fun serverTest() {
        HobaServer(1234, 1234)
    }
}