object StackConstants{
    val STACK_WORDS = 4194304u
}

class Stack : IStack {

    private val stack = mutableListOf<UInt>()

    override fun getTop(): UInt {
        return if(stack.size.toUInt() > 0u) stack[0]
        else 0u
    }

    override fun getSecond(): UInt{
        return if(stack.size.toUInt() > 1u) stack[1]
        else 0u
    }

    override fun push(a: UInt){
        if(stack.size.toUInt() < StackConstants.STACK_WORDS) stack.add(0, a)
    }

    override fun pop(){
        if(stack.size.toUInt() > 0u) stack.removeAt(0)
    }
}