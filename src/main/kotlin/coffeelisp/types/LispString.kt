package coffeelisp.types

import coffeelisp.syntax.Atom

data class LispString(val s: String): LispObject {
    override fun display() = "\"$s\""

    override fun type() = LispType("String")

    companion object {
        fun isType(atom: Atom): Boolean {
            return atom.token.startsWith('"') && atom.token.endsWith('"')
        }

        fun toLispObject(atom: Atom): LispObject {
            return LispString(atom.token.substring(1, atom.token.lastIndex))
        }
    }
}
