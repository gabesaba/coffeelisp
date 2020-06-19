package coffeelisp.types

import coffeelisp.env.Env
import coffeelisp.syntax.Lisp

class Fn(val name: String, private val fn: (List<Lisp>, Env) -> LispObject): LispObject {
    fun apply(expressions: List<Lisp>, env: Env) = fn(expressions, env)

    fun register(): Pair<String, Fn> = Pair(this.name.toLowerCase(), this)

    override val typeName = "Fn"

    override fun display() = "Fn"
}
