package coffeelisp.functions

import coffeelisp.types.LispBool

fun Boolean.toLispBool(): LispBool {
    return if (this) {
        LispBool.True
    } else {
        LispBool.False
    }
}
