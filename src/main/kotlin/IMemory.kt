interface IMemory {
    fun load(loc: UInt) : UInt
    fun save(loc: UInt, value: UInt)
    fun saveDiskImage()
    fun clearRam()
    fun loadDiskImage()
}