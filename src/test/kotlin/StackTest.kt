import org.junit.Test
import org.junit.jupiter.api.BeforeEach
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

internal class StackTest {

    private var stack = Stack()

    @BeforeEach
    fun resetStack(){
        stack = Stack()
    }

    @Test
    fun whenStackIsEmptyPoppingDoesNothing(){
        stack.pop()

        assertEquals(0u, stack.getTop().or(stack.getSecond()))
    }

    @Test
    fun whenPushingTopNumberIsEqualToPushedNumber(){
        stack.push(10u)

        assertEquals(10u, stack.getTop(),)
    }

    @Test
    fun whenPushingTwiceSecondNumberIsEqualToFirstPushedNumber(){
        stack.push(1u)
        stack.push(2u)

        assertEquals(1u, stack.getSecond())
    }

    @Test
    fun whenPoppingANumberTopNumberChanges(){
        stack.push(1u)

        var top = stack.getTop()

        stack.pop()

        assertNotEquals(top, stack.getTop())
    }

    @Test
    fun whenPoppingANumberSecondNumberChanges(){
        stack.push(1u)
        stack.push(2u)

        var second = stack.getTop()

        stack.pop();

        assertNotEquals(second, stack.getSecond())
    }

}