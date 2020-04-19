package coffeelisp.env

import coffeelisp.syntax.parseLisp
import coffeelisp.types.LispObject

var globalEnv: Env = createGlobalEnv()

fun interpret(s: String): LispObject {
    return parseLisp(s).eval(globalEnv)
}

fun repl() {
    var line: String?
    line = getLine()
    while (line != null) {
        try {
            println(interpret(line))
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
