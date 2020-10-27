package coffeelisp.types

import coffeelisp.syntax.Atom
import java.math.BigInteger

data class LispNumber(val num: BigInteger): LispObject {

    override val typeName = "Num"

    override fun display(): String {
        return num.toString()
    }

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
