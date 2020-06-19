package coffeelisp.types

import coffeelisp.env.Env
import coffeelisp.syntax.Lisp

interface LispObject: Lisp {
    val typeName: String

    fun display(): String

    override fun eval(env: Env): LispObject {
        return this
    }
}

class TypeError(override val message: String): Exception()
