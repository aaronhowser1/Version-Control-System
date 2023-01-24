package svcs

val commandHelp = """
    These are SVCS commands:
    config     Get and set a username.
    add        Add a file to the index.
    log        Show commit logs.
    commit     Save changes.
    checkout   Restore a file.
""".trimIndent()

fun main(args: Array<String>) {

    when (val input = args.firstOrNull()) {
        null,"","--help" -> println(commandHelp)
        "config" -> println("Get and set a username.")
        "add" -> println("Add a file to the index.")
        "log" -> println("Show commit logs.")
        "commit" -> println("Save changes.")
        "checkout" -> println("Restore a file.")
        else -> println("'${input}' is not a SVCS command.")
    }
}