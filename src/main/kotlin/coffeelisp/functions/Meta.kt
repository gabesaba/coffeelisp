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

val definitions = Fn("definitions") { _, env ->
    // TODO: Change this to a list type
    LispString(env.getDefinitions().joinToString(" ", "(", ")"))
}
