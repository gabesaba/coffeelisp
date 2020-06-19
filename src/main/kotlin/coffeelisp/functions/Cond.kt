package coffeelisp.functions

import coffeelisp.types.Fn
import coffeelisp.types.LispBoolean
import coffeelisp.syntax.LispError

val if27 = Fn("if") { args, env ->

    valArgs(args, 3, "if")

    when (args[0].eval(env)) {
        LispBoolean.True -> args[1].eval(env)
        LispBoolean.False -> args[2].eval(env)
        else -> throw LispError("if expects first arg to be predicate")
    }
}
