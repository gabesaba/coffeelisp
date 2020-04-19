package coffeelisp

import coffeelisp.syntax.SyntaxError
import coffeelisp.syntax.Atom
import coffeelisp.syntax.parseLisp
import coffeelisp.syntax.s
import coffeelisp.syntax.a
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class SyntaxTest {
    @Test fun testSimple() {

        val lisp1 = parseLisp("+")
        assertEquals(Atom("+"), lisp1)

        val lisp2 = parseLisp("(+ 5 5)")
        assertEquals(s(a("+"), a("5"), Atom("5")), lisp2)
    }

    @Test fun testNested() {
        val lisp = parseLisp("(+ (- 3 2) (/ 5 3))")
        assertEquals(
                s(a("+"),
                        s(a("-"), a("3"), a("2")),
                        s(a("/"), a("5"), a("3"))), lisp)
    }

    @Test fun testEmptyList() {
        assertEquals(s(), parseLisp("()"))
    }

    @Test fun testSyntaxError() {
        assertFailsWith(SyntaxError::class) {
            parseLisp("(+ 5 5))")
        }

        assertFailsWith(SyntaxError::class) {
            parseLisp(")(")
        }
    }
}
