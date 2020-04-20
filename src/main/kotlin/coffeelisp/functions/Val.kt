package coffeelisp.functions

import coffeelisp.syntax.Lisp
import coffeelisp.syntax.LispError

fun valArgs(args: List<Lisp>, expected: Int, fnName: String) {
    if (args.size != expected) {
        throw LispError("$fnName expected $expected args. Got ${args.size}")
    }
}
