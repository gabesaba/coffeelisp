package coffeelisp

import coffeelisp.env.eval
import coffeelisp.types.LispNumber
import coffeelisp.types.LispType
import coffeelisp.types.LispUnit
import coffeelisp.env.interpret
import kotlin.test.Test
import kotlin.test.assertEquals

class InterpreterTest {
    @Test fun define() {
        assertEquals(LispUnit, interpret("(define a 5)"))
        assertEquals(LispType("Num"), interpret("(type? a)"))
    }

    @Test fun type() {
        assertEquals(LispType("Fn"), interpret("(type? type?)"))
        assertEquals(LispType("Type"), interpret("(type? (type? 5))"))
        assertEquals(LispType("Type"), interpret("(type? (type? type?))"))
    }

    @Test fun recursion() {
        "(define zero (lambda (x) (= 0 x)))".eval()
        "(define sub1 (lambda (x) (- x 1)))".eval()
        "(define fact (lambda (x) (if (zero? x) 1 (* x (fact (sub1 x))))))".eval()
        assertEquals(LispNumber(720.toBigInteger()), interpret("(fact 6)"))
    }
}
