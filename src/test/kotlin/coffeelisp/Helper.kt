package coffeelisp;

import coffeelisp.env.eval
import kotlin.test.assertEquals
import kotlin.test.assertFails

// Test lisp expression against expected display
fun String.assertEquals(expected: String) {
    assertEquals(expected, this.eval())
}

fun String.assertFails() {
    assertFails {
        this.eval()
    }
}
