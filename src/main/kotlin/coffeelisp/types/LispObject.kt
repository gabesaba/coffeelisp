package coffeelisp.types

import coffeelisp.env.Env
import coffeelisp.syntax.Atom
import coffeelisp.syntax.Expression
import java.math.BigInteger

interface LispObject {
    fun type(): LispType

    fun display(): String
}

data class LispNumber(val num: BigInteger): LispObject {

    override fun display(): String {
        return num.toString()
    }

    override fun type() = LispType("Num")


    companion object {
        private val numberRegex = "-?[0-9]+".toRegex()

        fun isType(atom: Atom): Boolean {
            return numberRegex.matches(atom.token)
        }

        fun toLispObject(atom: Atom): LispObject {
            return LispNumber(atom.token.toBigInteger())
        }
    }
}

sealed class LispBool: LispObject {
    override fun type()= LispType("Bool")

    object True: LispBool() {
        override fun display() = "#t"
    }

    object False: LispBool() {
        override fun display() = "#f"
    }


    companion object {
        fun isType(atom: Atom): Boolean {
            return atom.token == "#f" || atom.token == "#t"
        }

        fun toLispObject(atom: Atom): LispObject {
            return when (atom.token) {
                "#t" -> True
                "#f" -> False
                else -> throw TypeError("Not valid LispBool")
            }
        }
    }
}

class Fn(val name: String, private val fn: (List<Expression>, Env) -> LispObject): LispObject {
    fun apply(expressions: List<Expression>, env: Env) = fn(expressions, env)

    fun register(): Pair<String, Fn> = Pair(this.name.toLowerCase(), this)

    override fun type() = LispType("Fn")

    override fun display() = "The Fn $name"
}

object LispUnit: LispObject {
    override fun type() = LispType("Unit")

    override fun display() = "()"

    fun isType(atom: Atom): Boolean {
        return atom.token == "()"
    }
}

data class LispType(private val name: String): LispObject {
    override fun type() = LispType("Type")

    override fun display() = name

    override fun toString() = name
}


class TypeError(override val message: String): Exception()
