class Alu : IAlu {
    override var result :UInt = 0u
    private set
    override var a :UInt = 0u
        set(value) {
            field = value
            compute()
        }
    override var b :UInt = 0u
        set(value) {
            field = value
            compute()
        }
    override var fcode :UInt = 0u
        set(value) {
            field = value
            compute()
        }

    private fun compute(){
        when(fcode){
            0u -> result = a+b
            1u -> result = a-b
            2u -> result = a*b
            3u -> result = a/b
            4u -> result = a shl b.toInt()
            5u -> result = a shr b.toInt()
            6u -> result = a.inv()
            7u -> result = a.and(b)
            8u -> result = a.or(b)
            9u -> result = a.xor(b)
            10u -> result = 0xFFFFFFFFu
            11u -> result = 0u
            12u -> result = a
            13u -> result = b
            14u -> result = a+1u
            15u -> result = a-1u
        }
    }
}
