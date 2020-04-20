package coffeelisp;

import coffeelisp.env.interpret
import kotlin.test.assertEquals
import kotlin.test.assertFails

// Test lisp expression against expected display
fun String.assertEquals(expected: String) {
    assertEquals(expected, interpret(this).display())
}

fun String.assertFails() {
    assertFails {
        interpret(this)
    }
}
