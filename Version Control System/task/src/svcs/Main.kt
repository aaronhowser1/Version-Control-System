package svcs

import java.io.File

val separator: String = File.separator
val vcsFolder = ensureFileExists("Version Control System${separator}task${separator}src${separator}svcs${separator}vcs", isDirectory = true)
val configFile = ensureFileExists("$vcsFolder${separator}config.txt")
val indexFile = ensureFileExists("$vcsFolder${separator}index.txt")

var currentUser : String?
    set(value) = configFile.writeText(value.toString())
    get() = configFile.readText()

fun main() {

    while (true) {
        val input = readln().split(" ")
        val command = input.firstOrNull()
        val args = input - input[0]
//        println("""
//            Input: $input
//            Command: $command
//            Arguments: ${args.joinToString(" ")}
//        """.trimIndent())
        when (command) {
            null,"","--help" -> help(args.firstOrNull())
            "config" -> config(args)
            "add" -> add(args)
            "log" -> log(args)
            else -> println("'${command}' is not a SVCS command.")
        }
    }
}

fun help(arg: String?) {
    val commandsList = """
        These are SVCS commands:
        config     Get and set a username.
        add        Add a file to the index.
        log        Show commit logs.
        commit     Save changes.
        checkout   Restore a file.
    """.trimIndent()
    when (arg) {
        null, "" -> println(commandsList)
        "config" -> println("Get and set a username.")
        "add" -> println("Add a file to the index.")
        "log" -> println("Show commit logs.")
        "commit" -> println("Save changes.")
        "checkout" -> println("Restore a file.")
    }
}

fun config(args: List<String>) {
    when (val newUser = args.firstOrNull()) {
        null -> {
            when (currentUser) {
                null,"" -> println("Please, tell me who you are.")
                else -> println("The username is $currentUser.")
            }
        }
        else -> {
            currentUser = newUser
            println("The username is $currentUser.")
        }
    }
}

fun add(args: List<String>) {
    when (args.firstOrNull()) {
        null -> help("add")
    }
}

fun log(args: List<String>) {
    when (args.firstOrNull()) {
        null -> null
    }
}

fun ensureFileExists(pathname: String, isDirectory: Boolean = false): File {
    val file = File(pathname)
//    val file = File("Version Control System${separator}task${separator}src${separator}svcs${separator}$pathname")
    if (!file.exists()) {
        if (isDirectory) {
            file.mkdir()
        } else {
            file.createNewFile()
        }
    }
    return file
}