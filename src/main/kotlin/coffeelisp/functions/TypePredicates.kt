package coffeelisp.functions

import coffeelisp.types.ConsCell
import coffeelisp.types.Fn
import coffeelisp.types.LispBoolean
import coffeelisp.types.LispNumber
import coffeelisp.types.LispString
import coffeelisp.types.LispUnit
import coffeelisp.types.toLispBoolean

private inline fun <reified T>createTypePredicate(name: String): Fn {
    return Fn(name) { args, env ->
        valArgs(args, 1, name)
        (args[0].eval(env) is T).toLispBoolean()
    }
}

val isBoolean = createTypePredicate<LispBoolean>("boolean?")
val isConsCell = createTypePredicate<ConsCell>("cons?")
val isFn = createTypePredicate<Fn>("fn?")
val isNumber = createTypePredicate<LispNumber>("number?")
val isString = createTypePredicate<LispString>("string?")
val isUnit = createTypePredicate<LispUnit>("unit?")
