package coffeelisp.syntax

import coffeelisp.env.Env
import coffeelisp.types.Fn
import coffeelisp.types.LispBoolean
import coffeelisp.types.LispNumber
import coffeelisp.types.LispObject
import coffeelisp.types.LispString
import coffeelisp.types.LispUnit
import coffeelisp.types.TypeError
import java.lang.Exception

interface Lisp {
    fun eval(env: Env): LispObject
}

sealed class Expression: Lisp

data class SymbolicExpression(val exprs: List<Expression>): Expression() {
    override fun eval(env: Env): LispObject {
        val fn = exprs[0].eval(env)
        return if (fn is Fn) {
            fn.apply(exprs.subList(1, exprs.size), env)
        } else {
            throw TypeError("Expected function, got ${fn.typeName}")
        }
    }
}

data class Atom(val token: String): Expression() {
    override fun eval(env: Env): LispObject {

        val symbol = env.find(token)

        return when {
            symbol != null -> symbol
            LispNumber.isType(this) -> LispNumber.toLispObject(this)
            LispBoolean.isType(this) -> LispBoolean.toLispObject(this)
            LispString.isType(this) -> LispString.toLispObject(this)
            LispUnit.isType(this) -> LispUnit
            else -> throw LispError("Not found: $token. Try (definitions)")
        }
    }
}

private val identifierRegex = "\\D.*".toRegex()
fun Atom.isIdentifier() = this.token.matches(identifierRegex)

fun s(vararg exprs: Expression) = SymbolicExpression(exprs.toList())
fun a(s: String) = Atom(s)

class LispError(override val message: String): Exception()
