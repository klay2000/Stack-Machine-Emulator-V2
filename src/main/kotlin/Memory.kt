import MemoryConstants.FLASH_SIZE
import MemoryConstants.RAM_SIZE
import java.io.BufferedReader
import java.util.*

object MemoryConstants{
    const val RAM_SIZE = 1048576u // one mega-word (4 MB)
    const val FLASH_SIZE = 1048576u // one mega-word (4 MB)
}

class Memory : IMemory {
    private val ram = Array<UInt>(RAM_SIZE.toInt()) { _ -> 0u }
    private val flash = Array<UInt>(FLASH_SIZE.toInt()) { _ -> 0u }

    override fun load(loc: UInt) : UInt{
        return if(loc >= MemoryConstants.FLASH_SIZE){
            //address is in ram
            val ramAddr = loc-MemoryConstants.FLASH_SIZE

            ram[ramAddr.toInt()] ?: 0u
        } else{
            //address is in flash
            flash[loc.toInt()] ?: 0u
        }
    }

    override fun save(loc: UInt, value: UInt){
        if(loc >= MemoryConstants.FLASH_SIZE){
            //address is in ram
            val ramAddr = loc-MemoryConstants.FLASH_SIZE

            ram[ramAddr.toInt()] = value
        }
        else{
            //address is in flash

            flash[loc.toInt()] = value

        }
    }

    override fun saveDiskImage(){
        // TODO save flash to file on system
    }

    override fun clearRam(){
        ram.fill(0u)
    }

    override fun loadDiskImage(){
        // TODO load flash from file on system
    }
}