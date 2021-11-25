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
        val file = File("flash.hex")

        if(file.exists()){
            file.delete()
        }

        val intFlash = Array<Int>(flash.size) { i -> flash[i].toInt()}

        val bb = ByteBuffer.allocate(intFlash.size*4)
        bb.asIntBuffer().put(intFlash.toIntArray())

        file.writeBytes(bb.array())
    }

    override fun clearRam(){
        ram.fill(0u)
    }

    override fun loadDiskImage(){
        val file = File("flash.hex")

        if(file.exists()) {
            val bArr = file.readBytes()

            for (i in flash.indices) {
                val word = byteArrayOf(bArr[i * 4], bArr[i * 4 + 1], bArr[i * 4 + 2], bArr[i * 4 + 3])
                flash[i] = ByteBuffer.wrap(word).getInt().toUInt()
            }
        }
    }
}