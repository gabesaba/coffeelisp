package coffeelisp.syntax

import coffeelisp.env.Env
import coffeelisp.types.Fn
import coffeelisp.types.LispNumber
import coffeelisp.types.LispObject
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

        val num = token.toLongOrNull()

        if (num != null) {
         return LispNumber(num)
        }

        val type = env.find(token)
        if (type != null) {
            return type
        } else {
            throw LispError("Not found: $token. Try (definitions)")
        }
    }

    fun isIdentifier() = token.matches("[a-zA-Z][A-Za-z0-9]*".toRegex())
}

fun s(vararg exprs: Expression) = SymbolicExpression(exprs.toList())
fun a(s: String) = Atom(s)

class LispError(override val message: String): Exception()
