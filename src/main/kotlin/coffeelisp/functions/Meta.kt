package coffeelisp.functions

import coffeelisp.types.Fn
import coffeelisp.syntax.LispError
import coffeelisp.types.LispString
import coffeelisp.types.LispUnit
import coffeelisp.env.globalEnv
import coffeelisp.env.createGlobalEnv

val clear = Fn("clear!") { _, _ ->
    globalEnv = createGlobalEnv()
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
