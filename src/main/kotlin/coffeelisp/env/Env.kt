package coffeelisp.env

import coffeelisp.syntax.LispError
import coffeelisp.functions.add1
import coffeelisp.functions.clear
import coffeelisp.functions.define
import coffeelisp.functions.definitions
import coffeelisp.functions.if27
import coffeelisp.functions.lambda
import coffeelisp.functions.mul
import coffeelisp.functions.sub1
import coffeelisp.functions.type
import coffeelisp.functions.zero
import coffeelisp.types.LispObject

class Env(private val parent: Env? = null, private val registry: MutableMap<String, LispObject> = mutableMapOf()) {
    private val level: Int = if (parent == null) {
        0
    } else {
        parent.level + 1
    }

    init {
        if (level > 100) {
            throw LispError("Exceeded stack depth")
        }
    }

    fun find(symbol: String): LispObject? {
        val symbolLower = symbol.toLowerCase()
        return registry[symbolLower]?: parent?.find(symbolLower)
    }

    fun set(symbol: String, lispObject: LispObject) {
        registry[symbol.toLowerCase()] = lispObject
    }

    fun getDefinitions(): Set<String> {
        return registry.keys.union(parent?.getDefinitions() ?: emptySet())
    }

    fun isGlobalEnv() = parent == null
}

fun createGlobalEnv(): Env {
    return Env(registry = mutableMapOf(
            define.register(),
            lambda.register(),
            if27.register(),
            definitions.register(),
            clear.register(),
            type.register(),
            add1.register(),
            sub1.register(),
            mul.register(),
            zero.register()
    ))
}
