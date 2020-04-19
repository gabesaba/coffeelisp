package coffeelisp.functions

import coffeelisp.env.Env
import coffeelisp.types.Fn
import coffeelisp.syntax.SymbolicExpression
import coffeelisp.syntax.Atom
import coffeelisp.syntax.isIdentifier
import coffeelisp.types.TypeError

val lambda = Fn("Lambda") { outerArgs, definitionEnv ->
    valArgs(outerArgs, 2, "Lambda")

    val car = outerArgs[0]
    if (car !is SymbolicExpression) {
        throw TypeError("Formals must be a S-Expression")
    }

    val formals = car.exprs.map {
        if (it is Atom && it.isIdentifier()) {
            it
        } else {
            throw TypeError("Lambda expects identifiers as formals")
        }
    }

    val lambdaFn = outerArgs[1]
    Fn("Lambda") { args, callingEnv ->

        valArgs(args, formals.size, "Lambda")


        val objects = args.map { it.eval(callingEnv) }
        val newEnv = Env(definitionEnv)

        for ((formal, arg) in formals.zip(objects)) {
            newEnv.set(formal.token, arg)
        }
        lambdaFn.eval(newEnv)
    }
}
