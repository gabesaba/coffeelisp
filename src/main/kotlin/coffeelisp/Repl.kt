package coffeelisp

import coffeelisp.env.Interpreter

fun repl() {
    val interpeter = Interpreter()
    var line: String?
    line = getLine()
    while (line != null) {
        try {
            println(interpeter.eval(line))
        } catch (e: Exception) {
            println("Error: $e")
        }
        line = getLine()
    }
}

private fun getLine(): String? {
    print("-> ")
    return readLine()
}
