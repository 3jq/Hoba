# Hoba - Modern Messenger written in kotlin
[![CodeFactor](https://www.codefactor.io/repository/github/fuckyouthinkimboogieman/hoba/badge)](https://www.codefactor.io/repository/github/fuckyouthinkimboogieman/hoba)

A messenger written in kotlin. Encrypts your messages with AES.

## Requirements
- Internet access;
- Windows/Linux/Mac, other OS are not supported;
- Java 8 or higher.

## Hosting server for a chat
1. Download jar from <a href="https://github.com/fuckyouthinkimboogieman/Hoba/releases"> releases </a> or build it yourself.
2. Upload it on your server.
3. Run the jar: "java -jar hoba.jar hostserver {(Integer) Port} {(Integer) Authorization Key}"

## Connecting to a chat
1. Download jar from <a href="https://github.com/fuckyouthinkimboogieman/Hoba/releases"> releases </a> or build it yourself.
2. Run the jar: "java -jar hoba.jar connect {IP} {(Integer) Port} {(Integer) Authorization Key}"