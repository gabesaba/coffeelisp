package coffeelisp.syntax

import java.lang.StringBuilder

fun parseLisp(s: String): Expression {

    val tokens = lex(s).toMutableList()
    val lisp = syntax(tokens)

    return if (tokens.isEmpty()) {
        lisp
    } else {
        throw SyntaxError()
    }
}

fun lex(lisp: String): List<String> {
    var pos = 0
    val result = mutableListOf<String>()

    val token = StringBuilder()
    fun flush() {
        if (token.isNotEmpty()) {
            result.add(token.toString())
            token.clear()
        }
    }

    while (pos < lisp.length) {
        val c = lisp[pos]
        when {
            c.isWhitespace() -> {
                flush()
                ++pos
            }
            c == '(' || c == ')' -> {
                flush()
                result.add(c.toString())
                ++pos
            }
            c == '"' -> {
                flush()
                val end = lisp.indexOf('"', pos + 1)
                if (end == -1) {
                    throw SyntaxError()
                }
                result.add(lisp.substring(pos, end + 1))
                pos = end + 1
            }
            else -> {
                token.append(c)
                ++pos
            }
        }
    }
    flush()
    return result
}

fun <T> MutableList<T>.pop(): T {
    return this.removeAt(0)
}

fun <T> MutableList<T>.unpop(t: T) {
    this.add(0, t)
}

fun syntax(tokens: MutableList<String>): Expression {
    if (tokens.isEmpty()) {
        return unitAtom
    }

    when (val token = tokens.pop()) {
        ")" -> throw SyntaxError()
        "(" -> {
            val sExp = mutableListOf<Expression>()
            while (tokens.size > 0) {
                val car = tokens.pop()
                when (car) {
                    ")" -> {
                        return if (sExp.isEmpty()) {
                            unitAtom
                        } else {
                            SymbolicExpression(sExp)
                        }
                    }
                    "(" -> {
                        tokens.unpop(car)
                        sExp.add(syntax(tokens))
                    }
                    else -> sExp.add(Atom(car))
                }
            }
        }
        else -> return Atom(token)
    }
    throw SyntaxError()
}

class SyntaxError: Exception()
