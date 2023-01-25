package svcs

import java.io.File

val s: String = File.separator
// Commented version keeps the tracked files with the Main.kt
//val workingFolder = "Version Control System${s}task${s}src${s}svcs${s}"
const val workingFolder = ""
val vcsFolder = ensureFileExists("${workingFolder}vcs", isDirectory = true)
val configFile = ensureFileExists("$vcsFolder${s}config.txt")
val indexFile = ensureFileExists("$vcsFolder${s}index.txt")

var currentUser : String?
    set(value) = configFile.writeText(value.toString())
    get() = configFile.readText()

var trackedFiles : List<String>
    set(value) = indexFile.writeText(value.joinToString("\n"))
    get() = indexFile.readLines()

fun main(args: Array<String>) {
    println(args.joinToString(" "))
    println("test")
    if (args.firstOrNull() == "--help") {
        help()
    }

    while (true) {
        val input = readln().split(" ")
        val command = input.firstOrNull()
        val commandArgs = input - input[0]
        when (command) {
            null,"","help","--help" -> help(commandArgs.firstOrNull())
            "config" -> config(commandArgs)
            "add" -> add(commandArgs)
            "log" -> log(commandArgs)
            else -> println("'${command}' is not a SVCS command.")
        }
    }
}

fun help(arg: String? = "") {
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
    val newUser = args.firstOrNull()
    if (newUser == null) {
        if (currentUser.isNullOrBlank()) {
            println("Please, tell me who you are.")
        } else {
            println("The username is $currentUser.")
        }
    } else {
        currentUser = newUser
        println("The username is $currentUser.")
    }
}

fun add(args: List<String>) {
    if (args.isNotEmpty()) {
        val fileToAdd = File("${workingFolder}${args.first()}")
        if (fileToAdd.exists()) {
            indexFile.appendText(args.first())
            println("The file '${args.first()}' is tracked.")
        } else {
            println("Can't find '${args.first()}'.")
        }
    } else {
        if (trackedFiles.isEmpty()) {
            help("add")
        } else {
            printTrackedFiles()
        }
    }
}

fun printTrackedFiles() {
    println("Tracked files:")
    for (file in trackedFiles) {
        println(file)
    }
}

fun log(args: List<String>) {
    when (args.firstOrNull()) {
        null -> help("log")
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