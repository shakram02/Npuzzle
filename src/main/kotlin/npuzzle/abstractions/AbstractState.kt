package npuzzle.abstractions

interface AbstractState<T> {
    val stateRepresentation: Succesorable<T>
    val parent: AbstractState<T>?
    override fun hashCode(): Int
    fun getSuccessors(): List<AbstractState<T>>
}