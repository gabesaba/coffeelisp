package coffeelisp.functions

import coffeelisp.syntax.LispError
import coffeelisp.types.Fn
import coffeelisp.types.LispNumber
import java.math.BigInteger
import kotlin.random.Random

val random = Fn("Random") { exprs, env ->
    if (1 != exprs.size) {
        throw LispError("Define expects 1 arg")
    }

    val limit = exprs[0].eval(env)

    return@Fn when {
        limit !is LispNumber -> throw LispError("Expect numerical arg")
        limit.num > Int.MAX_VALUE.toBigInteger() || limit.num < BigInteger.ONE -> throw LispError("Out of range")
        else -> {
            val asInt = limit.num.toInt()
            LispNumber(Random.Default.nextInt(asInt).toBigInteger())
        }
    }
}
