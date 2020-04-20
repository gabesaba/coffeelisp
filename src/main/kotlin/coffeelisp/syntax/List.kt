package coffeelisp.syntax

import coffeelisp.types.ConsCell
import coffeelisp.types.LispObject
import coffeelisp.types.LispUnit


// TODO: may want to unify representation
// of these two types
fun List<LispObject>.toCoffeeList(): LispObject {
    return if (this.isEmpty()) {
        LispUnit
    } else {
        ConsCell(this.first(), this.subList(1, this.size).toCoffeeList())
    }
}

fun LispObject.toExpressionList(): List<Lisp> {
    return when (this) {
        is ConsCell -> {
            val res = mutableListOf<Lisp>(this.car)
            res.addAll(this.cdr.toExpressionList())
            res
        }
        is LispUnit -> listOf()
        else -> listOf(this)
    }
}
