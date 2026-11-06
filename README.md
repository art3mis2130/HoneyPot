# HoneyPot

A minimal Java HTTP honeypot for learning about socket programming and observing incoming network requests in a controlled lab.

## Features

- Listens for TCP connections on port `8080`.
- Prints the connecting client's IP address to the console.
- Reads and prints up to 1,024 bytes from each connection.
- Sends a fixed HTTP-like `200 OK` response and closes the connection.
- Uses only the Java standard library; no external dependencies.

## Requirements

- A Java Development Kit (JDK) with `java` and `javac` on your PATH.
- JDK 22 matches the original IntelliJ project configuration. GitHub CI also compiles with JDK 17 and 21.
- Port 8080 available on your machine.

## Build and run

From the repository root:

```sh
javac -d out src/Main.java src/basicHoney/HttpHP.java
java -cp out basicHoney.HttpHP
```

The server prints `Acknowledge ->8080` when it starts. Stop it with **Ctrl+C**.

`src/Main.java` is the original Hello World starter; the honeypot entry point is `basicHoney.HttpHP`.

In IntelliJ IDEA, open the repository, select a JDK, mark `src` as the sources root if necessary, and run `basicHoney.HttpHP.main()`.

## Try a local request

In a second terminal:

```sh
curl --verbose --max-time 5 http://127.0.0.1:8080/
```

On Windows PowerShell, use `curl.exe` to avoid older PowerShell's `curl` alias. The server console should show the client address and request data. Some HTTP clients may reject the prototype's response format; inspect the server console to confirm receipt.

## Project structure

```text
src/
  Main.java                 # Original starter program
  basicHoney/
    HttpHP.java             # Honeypot server and request handler
.github/workflows/java.yml  # Compilation checks
```

## Current limitations

This is an educational prototype, not a production-ready security service:

- Binds to all available network interfaces on a hardcoded port.
- Handles one client at a time without a read timeout; an idle client can block subsequent requests.
- Performs a single read of at most 1,024 bytes, so requests may be incomplete.
- Does not handle end-of-stream before constructing the request string; a client closing without data can terminate the server with an unchecked exception.
- Uses platform-default text encoding and prints unfiltered request data to the console.
- The fixed response uses LF line endings and `Content Length` instead of the standard `Content-Length` header.
- No TLS, persistent logs, dashboard, alerting, protocol emulation, or automated behavior tests.

Run only on systems and networks you are authorized to monitor. Use an isolated lab and firewall rules to control access; request logs may contain sensitive data.

## Contributing

See [CONTRIBUTING.md](CONTRIBUTING.md) for development and verification steps. Useful improvements include valid HTTP response framing, EOF handling, timeouts, configurable bind settings, and automated socket tests.

## License

Licensed under the [MIT License](LICENSE).
