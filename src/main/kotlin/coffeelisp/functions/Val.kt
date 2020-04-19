package coffeelisp.functions

import coffeelisp.syntax.Expression
import coffeelisp.syntax.LispError

fun valArgs(args: List<Expression>, expected: Int, fnName: String) {
    if (args.size != expected) {
        throw LispError("$fnName expected $expected args. Got ${args.size}")
    }
}
