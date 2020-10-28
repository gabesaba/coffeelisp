package coffeelisp

import coffeelisp.env.eval
import coffeelisp.errors.RecursionDepthExceeded
import kotlin.test.Test
import kotlin.test.assertFailsWith

class InterpreterTest {
    @Test
    fun define() {
        "(define a 5)".assertEquals("()")
        "a".assertEquals("5")
    }

    @Test
    fun type() {
        "(type? type?)".assertEquals("Fn")
        "(type? (type? 5))".assertEquals("Type")
        "(type? (type? type?))".assertEquals("Type")
    }

    @Test
    fun recursion() {
        "(define zero? (lambda (x) (= 0 x)))".eval()
        "(define sub1 (lambda (x) (- x 1)))".eval()
        "(define fact (lambda (x) (if (zero? x) 1 (* x (fact (sub1 x))))))".eval()
        "(fact 6)".assertEquals("720")
    }

    @Test
    fun testNoStackSmashing() {
        "(define recur (lambda () (recur)))".eval()
        assertFailsWith(RecursionDepthExceeded::class) {
            "(recur)".eval()
        }
    }

    @Test
    fun testEvalsToUnit() {
        "".assertEquals("()")
        "            ".assertEquals("()")
        "()".assertEquals("()")
    }
}
