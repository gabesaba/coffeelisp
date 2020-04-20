package coffeelisp.functions

import coffeelisp.syntax.toExpressionList
import coffeelisp.types.Fn

val apply = Fn("apply") { args, env ->
    valArgs(args, 2, "apply")
    val fn = args.first().eval(env) as Fn
    val argList = args[1].eval(env).toExpressionList()
    fn.apply(argList, env)
}
