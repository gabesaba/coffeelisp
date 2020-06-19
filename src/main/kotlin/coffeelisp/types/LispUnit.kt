package coffeelisp.types

import coffeelisp.syntax.Atom

object LispUnit: LispObject {
    override val typeName = "Unit"

    override fun display() = "()"

    fun isType(atom: Atom): Boolean {
        return atom.token == "()"
    }
}
