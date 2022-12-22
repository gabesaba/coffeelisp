package coffeelisp.functions

import coffeelisp.types.Fn
import coffeelisp.syntax.LispError
import coffeelisp.types.LispUnit
import coffeelisp.syntax.Atom
import coffeelisp.syntax.isIdentifier
import coffeelisp.types.TypeError

private val undefinable = setOf(reset.name, definitions.name)
val define = Fn("Define") { exprs, env ->
    when {
        !env.isRootEnv() -> throw LispError("Must call Define at top level")
        2 != exprs.size -> throw LispError("Define expects 2 args")
    }

    val car = exprs[0]
    if (car is Atom && car.isIdentifier()) {

        if (undefinable.contains(car.token.lowercase())) {
            throw LispError("Cannot redefine ${car.token}")
        }
        val res = exprs[1].eval(env)
        env.set(car.token, res)
        LispUnit
    } else {
        throw TypeError("Define expects first arg to be an identifier")
    }
}
