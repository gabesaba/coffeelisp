package coffeelisp.functions

import coffeelisp.env.Env
import coffeelisp.syntax.Lisp
import coffeelisp.syntax.LispError
import coffeelisp.types.Fn
import coffeelisp.types.LispBoolean
import coffeelisp.types.LispNumber
import coffeelisp.types.TypeError
import coffeelisp.types.toLispBoolean
import java.math.BigInteger

val plus = Fn("+") { args, env ->
    LispNumber(getNums(args, env).fold(BigInteger.ZERO) { a, b -> a + b })
}

val minus = Fn("-") { args, env ->
    if (args.isEmpty()) {
        throw LispError("- expects at least 1 arg!")
    }

    val nums = getNums(args, env)
    if (nums.size == 1) {
        LispNumber(-nums[0])
    } else {
        val res = nums.subList(1, nums.size).foldRight(nums.first()) { x, acc ->
            acc - x
        }
        LispNumber(res)
    }
}

val mul = Fn("*") { args, env ->
    LispNumber(getNums(args, env).fold(BigInteger.ONE) { a, b -> a * b })
}

val numEqual = Fn("=") { args, env ->

    if (args.isEmpty()) {
        return@Fn LispBoolean.True
    }

    val fst = getNum(args.first(), env)
    args.all { getNum(it, env) == fst }.toLispBoolean()
}

private fun getNum(expr: Lisp, env: Env): BigInteger {
    val v = expr.eval(env)
    return if (v is LispNumber) {
        v.num
    } else {
        throw TypeError("Expected num")
    }
}

private fun getNums(args: List<Lisp>, env: Env): List<BigInteger> {
    return args.map {
        getNum(it, env)
    }
}
