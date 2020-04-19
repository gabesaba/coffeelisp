package coffeelisp.functions

import coffeelisp.types.Fn
import coffeelisp.types.LispBool
import coffeelisp.syntax.LispError
import coffeelisp.types.LispNumber
import coffeelisp.types.LispUnit
import coffeelisp.types.TypeError
import java.math.BigInteger

val if27 = Fn("if") { args, env ->

    valArgs(args, 3, "if")

    when (args[0].eval(env)) {
        LispBool.True -> args[1].eval(env)
        LispBool.False -> args[2].eval(env)
        else -> throw LispError("if expects first arg to be predicate")
    }
}

val zero = Fn("zero?") { args, env ->
    valArgs(args, 1, "zero")

    val res = args[0].eval(env)

    if (res !is LispNumber) {
        throw TypeError("zero? expected Num")
    }

    if (res == LispNumber(BigInteger.ZERO)) {
        LispBool.True
    } else {
        LispBool.False
    }
}

val isUnit = Fn("unit?") { args, env ->
    valArgs(args, 1, "unit?")

    if (args[0].eval(env) is LispUnit) {
        LispBool.True
    } else {
        LispBool.False
    }
}
