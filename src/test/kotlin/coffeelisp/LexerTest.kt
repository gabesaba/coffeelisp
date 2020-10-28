package coffeelisp

import coffeelisp.syntax.SyntaxError
import coffeelisp.syntax.lex
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class LexerText {
    @Test fun testSimple() {
        val res = lex("(+ 5 5)")
        assertEquals(listOf("(", "+", "5", "5", ")"), res)
    }

    @Test fun testFunnySpaces() {
        val res = lex(" (   +   5    5 )")
        assertEquals(listOf("(", "+", "5", "5", ")"), res)
    }

    @Test fun testString() {
        val res = lex("\"helloworld\"")
        assertEquals(listOf("\"helloworld\""), res)
    }

    @Test fun testStringWithSpaces() {
        val res = lex("\"hello world haha\"")
        assertEquals(listOf("\"hello world haha\""), res)
    }

    @Test fun testEmpty() {
        val res = lex("")
        assertEquals(listOf(), res)
    }
}
