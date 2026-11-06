# Contributing

Open an issue describing the bug or proposed improvement before a substantial change. For a pull request, explain the behavior change and how you verified it.

## Development

1. Install a JDK and clone the repository.
2. Compile with `javac -Xlint:all -d out src/Main.java src/basicHoney/HttpHP.java`.
3. Run `java -cp out basicHoney.HttpHP` in an isolated environment.
4. Send a local test request and inspect the console output.
5. Stop the server and include verification results in your pull request.

Keep changes focused. Add meaningful automated tests when modifying socket handling. Do not commit credentials, captured traffic, personal information, compiled files, or local editor settings.

Contributions are provided under the repository's MIT license.
