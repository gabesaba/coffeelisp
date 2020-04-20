package coffeelisp.functions

import coffeelisp.env.Env
import coffeelisp.types.Fn
import coffeelisp.syntax.SymbolicExpression
import coffeelisp.syntax.Atom
import coffeelisp.syntax.Expression
import coffeelisp.syntax.isIdentifier
import coffeelisp.syntax.toCoffeeList
import coffeelisp.types.LispObject
import coffeelisp.types.TypeError

val lambda = Fn("Lambda") { outerArgs, definitionEnv ->
    valArgs(outerArgs, 2, "Lambda")

    val car = outerArgs[0] as Expression
    val formals = createFormals(car)

    val lambdaFn = outerArgs[1]
    Fn("Anon") { args, callingEnv ->
        val lispObjects = args.map { it.eval(callingEnv) }
        val newEnv = formals.bindArgs(lispObjects, definitionEnv)
        lambdaFn.eval(newEnv)
    }
}

// https://www.gnu.org/software/mit-scheme/documentation/mit-scheme-ref/Lambda-Expressions.html
sealed class Formals {
    abstract fun bindArgs(objects: List<LispObject>, definitionEnv: Env): Env
}

// Syntactic form for fixed-num args (a b c)
class FixedArgs(symbolicExpression: SymbolicExpression): Formals() {

    private val formals = symbolicExpression.exprs.map {
        if (it is Atom && it.isIdentifier()) {
            it
        } else {
            throw TypeError("Lambda expects identifiers as formals")
        }
    }
    override fun bindArgs(objects: List<LispObject>, definitionEnv: Env): Env {
        val newEnv = Env(definitionEnv)
        valArgs(objects, formals.size, "Lambda")
        for ((formal, arg) in formals.zip(objects)) {
            newEnv.set(formal.token, arg)
        }
        return newEnv
    }
}

// Syntactic form for varargs (#! rest a)
class VarArgs(atom: Atom): Formals() {
    init {
        require(atom.isIdentifier())
    }

    private val symbol = atom.token

    override fun bindArgs(lispObjects: List<LispObject>, definitionEnv: Env): Env {
        val newEnv = Env(definitionEnv)
        val argList = lispObjects.toCoffeeList()
        newEnv.set(symbol, argList)
        return newEnv
    }
}

fun createFormals(expr: Expression): Formals {
    return when (expr) {
        is SymbolicExpression -> {
           FixedArgs(expr)
        }
        is Atom -> {
            VarArgs(expr)
        }
    }
}
