import MemoryConstants.FLASH_SIZE
import MemoryConstants.RAM_SIZE
import java.io.BufferedReader
import java.io.File
import java.nio.ByteBuffer
import java.nio.IntBuffer
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
        var file = File("flash.hex")

        if(file.exists()){
            file.delete()
        }

        var intFlash = Array<Int>(flash.size) {i -> flash[i].toInt()}

        var bb = ByteBuffer.allocate(intFlash.size*4)
        bb.asIntBuffer().put(intFlash.toIntArray())

        file.writeBytes(bb.array())
    }

    override fun clearRam(){
        ram.fill(0u)
    }

    override fun loadDiskImage(){
        var bArr = File("flash.hex").readBytes()

        for(i in flash.indices){
            var word = (bArr[i*4].toInt() shr 24) + (bArr[i*4+1].toInt() shr 16) + (bArr[i*4+2].toInt() shr 8) + bArr[i*4+3].toInt()
            flash[i] = word.toUInt()
        }
    }
}