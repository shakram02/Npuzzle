//package npuzzle.abstractions
//
//import java.lang.IllegalArgumentException
//
//class Move<T : Number>(graph: PuzzleArray<T>, item: T, private val direction: AbstractDirection) {
//    private val result = graph.copyOf()
//
//
//    init {
//        val frontier = graph.getSuccessors(item)
//        when (direction) {
//            Direction.North -> (result as PuzzleArray).swap(item, frontier[Direction.North]!!)
//            Direction.South -> (result as PuzzleArray).swap(item, frontier[Direction.South]!!)
//            Direction.West -> (result as PuzzleArray).swap(item, frontier[Direction.West]!!)
//            Direction.East -> (result as PuzzleArray).swap(item, frontier[Direction.East]!!)
//            else -> IllegalArgumentException("Can't move by none")
//        }
//    }
//
//    fun getResult(): PuzzleArray<T> {
//        return result as PuzzleArray<T>
//    }
//
//    override fun toString(): String {
//        return "Moved $direction, Result $result"
//    }
//
//    override fun hashCode(): Int {
//        return result.hashCode()
//    }
//
//    override fun equals(other: Any?): Boolean {
//        if (this === other) return true
//        if (javaClass != other?.javaClass) return false
//
//        other as Move<*>
//
//        if (direction != other.direction) return false
//        if (result != other.result) return false
//
//        return true
//    }
//}