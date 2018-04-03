package npuzzle.abstractions

class State<T : Number>(override val stateRepresentation: Succesorable<T>, override val parent: State<T>? = null) : AbstractState<T> {

    private fun move(direction: AbstractDirection): State<T> {
        return State((stateRepresentation as PuzzleArray).move(direction), this)
    }

    // Generate states of moves in all possible directions
    override fun getSuccessors(): List<State<T>> {
        val result = mutableListOf<State<T>>()

        for (direction in stateRepresentation.getSuccessors().keys) {
            result.add(this.move(direction))
        }
        return result
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as State<*>

        return this.stateRepresentation.hashCode() == other.stateRepresentation.hashCode()
    }


    override fun toString(): String {
        return stateRepresentation.toString()
    }

    override fun hashCode(): Int {
        return stateRepresentation.hashCode()
    }

}