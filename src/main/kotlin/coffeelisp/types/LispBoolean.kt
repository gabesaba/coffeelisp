package coffeelisp.types

import coffeelisp.syntax.Atom

sealed class LispBoolean: LispObject {

    override val typeName = "Boolean"

    object True: LispBoolean() {
        override fun display() = "#t"
    }

    object False: LispBoolean() {
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

fun Boolean.toLispBoolean(): LispBoolean {
    return if (this) {
        LispBoolean.True
    } else {
        LispBoolean.False
    }
}
