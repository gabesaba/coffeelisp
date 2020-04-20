package coffeelisp.functions

import coffeelisp.assertEquals
import coffeelisp.env.eval
import org.junit.Test

class LambdaTest {

    @Test
    fun testNoArgs() {
        "(define five (lambda () 5))".eval()
        "(five)".assertEquals("5")
    }
    @Test
    fun testSquare() {
        "(define square (lambda (x) (* x x)))".eval()

        "(square 1)".assertEquals("1")
        "(square 3)".assertEquals("9")
        "(square 5)".assertEquals("25")
    }

    @Test
    fun testVarArgs() {
        "(define sum (lambda x (if (unit? x) 0 (+ (car x) (apply sum (cdr x))))))".eval()
        "(sum 1 2 3)".assertEquals("6")
    }

    @Test
    fun testList() {
        "(define list (lambda x x))".eval()
        "(list)".assertEquals("()")
        "(list 1)".assertEquals("(1)")
        "(list 1 2)".assertEquals("(1 2)")
        "(list 1 2 3)".assertEquals("(1 2 3)")
    }
}
