package coffeelisp;

import coffeelisp.env.interpret
import kotlin.test.assertEquals

// Test lisp expression against expected display
fun String.assertEquals(expected: String) {
    assertEquals(expected, interpret(this).display())
}
