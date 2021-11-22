import org.junit.Test
import kotlin.test.BeforeTest
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

internal class AluTest {
    private val alu = Alu()
    private val num1 = 12u
    private val num2 = 27u

    @BeforeTest
    fun resetAlu() {
        alu.a = num1
        alu.b = num2
        alu.fcode = 0u
    }


    @Test
    fun fCodeIsAdditionThenResultIsAPlusB() {
        alu.fcode = 0x0u
        assertEquals(39u, alu.result)
    }

    @Test
    fun fCodeIsSubtractionThenResultIsAMinusB() {
        alu.fcode = 0x1u
        assertEquals((-15).toUInt(), alu.result)
    }

    @Test
    fun fCodeIsMultiplicationThenResultIsATimesB() {
        alu.fcode = 0x2u
        assertEquals(324u, alu.result)
    }

    @Test
    fun fCodeIsDivisionThenResultIsADividedByB() {
        alu.fcode = 0x3u
        assertEquals(0u, alu.result)
    }

    @Test
    fun fCodeIsLeftShiftThenResultIsAShiftedLeftB() {
        alu.fcode = 0x4u
        assertEquals(1610612736u, alu.result)
    }

    @Test
    fun fCodeIsRightShiftThenResultIsAShiftedRightB() {
        alu.fcode = 0x5u
        assertEquals(0u, alu.result)
    }

    @Test
    fun fCodeIsNotThenResultIsNotA() {
        alu.fcode = 0x6u
        assertEquals(0xFFFFFFF3u, alu.result)
    }

    @Test
    fun fCodeIsAndThenResultIsAAndB() {
        alu.fcode = 0x7u
        assertEquals(0b01000u, alu.result)
    }

    @Test
    fun fCodeIsOrThenResultIsAOrB() {
        alu.fcode = 0x8u
        assertEquals(0b11111u, alu.result)
    }

    @Test
    fun fCodeIsXorThenResultIsAXorB() {
        alu.fcode = 0x9u
        assertEquals(0b10111u, alu.result)
    }

    @Test
    fun fCodeIsTrueThenResultIsTrue() {
        alu.fcode = 0xAu
        assertEquals(0xFFFFFFFFu, alu.result)
    }

    @Test
    fun fCodeIsFalseThenResultIsFalse() {
        alu.fcode = 0xBu
        assertEquals(0u, alu.result)
    }

    @Test
    fun fCodeIsPassAThenResultIsA() {
        alu.fcode = 0xCu
        assertEquals(12u, alu.result)
    }

    @Test
    fun fCodeIsPassBThenResultIsB() {
        alu.fcode = 0xDu
        assertEquals(27u, alu.result)
    }

    @Test
    fun fCodeIsIncrementAThenResultIsAPlusOne() {
        alu.fcode = 0xEu
        assertEquals(13u, alu.result)
    }

    @Test
    fun fCodeIsDecrementAThenResultIsAMinusOne() {
        alu.fcode = 0xFu
        assertEquals(11u, alu.result)
    }

    @Test
    fun fCodeIsUpdatedThenResultIsUpdated() {
        var res = alu.result

        alu.fcode += 1u

        assertNotEquals(res, alu.result)
    }

    @Test
    fun aIsUpdatedThenResultIsUpdated() {
        var res = alu.result

        alu.a += 1u

        assertNotEquals(res, alu.result)
    }

    @Test
    fun bIsUpdatedThenResultIsUpdated() {
        var res = alu.result

        alu.b += 1u

        assertNotEquals(res, alu.result)
    }
}