import junit.framework.Assert
import junit.framework.Assert.assertTrue
import org.junit.Test
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ArgumentsSource
import org.junit.jupiter.params.provider.ValueSource
import kotlin.test.assertEquals

internal class MemoryTest{

    var mem = Memory()

    @BeforeEach
    fun initializeMem() {
        mem = Memory()
    }

    @ParameterizedTest
    @ValueSource(ints = [0, 124,
                         7, 12,
                         332445, 3776,
                         262144, -2354])
    fun savedValuesCanBeLoaded(location : Int, value : Int) {
        mem.save(location.toUInt(), value.toUInt())
        assertEquals(value, mem.load(location.toUInt()).toInt())
    }

    @Test
    fun allValuesAreInitiallyZero() {
        var zero = true
        for(i in 0 .. (MemoryConstants.FLASH_SIZE+MemoryConstants.RAM_SIZE-1u).toInt()) {
            zero = zero && mem.load(i.toUInt()) == 0u
        }

        assertTrue(zero)
    }

    @Test
    fun clearRamClearsRam(){
        mem.save(MemoryConstants.FLASH_SIZE, 9674u)
        mem.clearRam()

        assertEquals(0u, mem.load(MemoryConstants.FLASH_SIZE))
    }

    @Test
    fun clearRamDoesNotClearFlash() {
        mem.save(0u, 9674u)
        mem.clearRam()

        assertEquals(9674u, mem.load(0u))
    }

}