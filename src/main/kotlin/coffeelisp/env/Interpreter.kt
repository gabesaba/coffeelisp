package coffeelisp.env

import coffeelisp.syntax.parseLisp

class Interpreter {
    private var env = createEnv()

    fun eval(lisp: String) = lisp.eval(env)
}

// hack for when we're too lazy to create an interpreter :)
private val defaultEnv = createEnv()

fun String.eval(env: Env = defaultEnv): String {
    return parseLisp(this).eval(env).display()
}
