package coffeelisp.syntax

import coffeelisp.env.Env
import coffeelisp.types.Fn
import coffeelisp.types.LispBool
import coffeelisp.types.LispNumber
import coffeelisp.types.LispObject
import coffeelisp.types.LispString
import coffeelisp.types.TypeError
import java.lang.Exception

sealed class Expression {
    abstract fun eval(env: Env): LispObject
}

data class SymbolicExpression(val exprs: List<Expression>): Expression() {
    override fun eval(env: Env): LispObject {
        val fn = exprs[0].eval(env)
        return if (fn is Fn) {
            fn.apply(exprs.subList(1, exprs.size), env)
        } else {
            throw TypeError("Expected function, got ${fn.type()}")
        }
    }
}

data class Atom(val token: String): Expression() {
    override fun eval(env: Env): LispObject {

        val type = env.find(token)

        return when {
            type != null -> type
            LispNumber.isType(this) -> LispNumber.toLispObject(this)
            LispBool.isType(this) -> LispBool.toLispObject(this)
            LispString.isType(this) -> LispString.toLispObject(this)
            else -> throw LispError("Not found: $token. Try (definitions)")
        }
    }

    fun isIdentifier() = token.matches("[a-zA-Z][A-Za-z0-9]*".toRegex())
}

fun s(vararg exprs: Expression) = SymbolicExpression(exprs.toList())
fun a(s: String) = Atom(s)

class LispError(override val message: String): Exception()
