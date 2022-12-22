package coffeelisp.env

import coffeelisp.errors.MAX_RECUSION_DEPTH
import coffeelisp.errors.RecursionDepthExceeded
import coffeelisp.functions.apply
import coffeelisp.syntax.LispError
import coffeelisp.functions.plus
import coffeelisp.functions.car
import coffeelisp.functions.cdr
import coffeelisp.functions.cons
import coffeelisp.functions.define
import coffeelisp.functions.definitions
import coffeelisp.functions.if27
import coffeelisp.functions.isUnit
import coffeelisp.functions.lambda
import coffeelisp.functions.library
import coffeelisp.functions.mul
import coffeelisp.functions.minus
import coffeelisp.functions.numEqual
import coffeelisp.functions.random
import coffeelisp.functions.reset
import coffeelisp.functions.type
import coffeelisp.types.LispObject

private val defaultRegistry = mapOf(
        define.register(),
        lambda.register(),
        apply.register(),
        if27.register(),
        definitions.register(),
        reset.register(),
        type.register(),
        cons.register(),
        car.register(),
        cdr.register(),
        isUnit.register(),
        plus.register(),
        minus.register(),
        mul.register(),
        numEqual.register(),
        random.register()
)

class Env(private val parent: Env? = null,
          private val registry: MutableMap<String, LispObject> = mutableMapOf(),
          val level: Int = 0) {

    init {
        // if we have a parent, level should not be default
        require(parent == null || level > 0)

        if (level > MAX_RECUSION_DEPTH) {
            throw RecursionDepthExceeded()
        }
    }

    fun find(symbol: String): LispObject? {
        val symbolLower = symbol.lowercase()
        return registry[symbolLower]?: parent?.find(symbolLower)
    }

    fun set(symbol: String, lispObject: LispObject) {
        registry[symbol.lowercase()] = lispObject
    }

    fun getDefinitions(): Set<String> {
        return registry.keys.union(parent?.getDefinitions() ?: emptySet())
    }

    fun isRootEnv() = parent == null

    fun initialize(): Env {
        require(isRootEnv())
        registry.clear()
        registry.putAll(defaultRegistry)
        loadLibrary()
        return this
    }

    private fun loadLibrary() {
        library.forEach {
            it.eval(this)
        }
    }
}

fun createEnv(): Env {
    return Env().initialize()
}
