enum class Opcodes {
    //2 arg
    ADD(),
    SUB(),
    MUL(),
    DIV(),
    SHL(),
    SHR(),
    AND(),
    OR(),
    XOR(),
    MOV(),
    SAV(),
    //1 arg
    LD(),
    NOT(),
    INC(),
    DEC(),
    JMP(),
    JLT(),
    JMT(),
    JEQ(),
    //no arg
    TRU(),
    FAL(),
    PSH(),
    POP(),
    IMM(),
    JIM()
}