package coffeelisp.functions

import coffeelisp.types.Fn
import coffeelisp.syntax.LispError
import coffeelisp.types.LispString
import coffeelisp.types.LispUnit

val reset = Fn("reset!") { _, env ->
    if (!env.isRootEnv()) {
        throw LispError("Must be called from root env")
    }
    env.initialize()
    LispUnit
}

val type = Fn("Type?") { exprs, env ->

    if (exprs.size != 1) {
        throw LispError("Type? expects exactly 1 arg")
    }
    exprs.first().eval(env).type()
}

val definitions = Fn("definitions") { _, env ->
    // TODO: Change this to a list type
    LispString(env.getDefinitions().joinToString(" ", "(", ")"))
}
