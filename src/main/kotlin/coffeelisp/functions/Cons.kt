package coffeelisp.functions

import coffeelisp.syntax.LispError
import coffeelisp.types.ConsCell
import coffeelisp.types.Fn

val cons = Fn("cons") { args, env ->
    valArgs(args, 2, "cons")
    ConsCell(args[0].eval(env), args[1].eval(env))
}

val car = Fn("car") { args, env ->
    valArgs(args, 1, "car")

    val arg = args[0].eval(env)
    if (arg is ConsCell) {
        arg.car
    } else {
        throw LispError("car expected cons cell")
    }
}

val cdr = Fn("cdr") { args, env ->
    valArgs(args, 1, "cdr")

    val arg = args[0].eval(env)
    if (arg is ConsCell) {
        arg.cdr
    } else {
        throw LispError("cdr expected cons cell")
    }
}
