package npuzzle.abstractions

import npuzzle.getFrontier
import npuzzle.graphHash
import npuzzle.swap
import java.lang.IllegalArgumentException
import java.util.*

class Move<T>(graph: Array<T>, item: T, private val direction: Direction) {
    private val result = graph.copyOf()


    init {
        val frontier = graph.getFrontier(item)
        when (direction) {
            Direction.North -> result.swap(item, frontier.first({ e -> e.direction == Direction.North }).value)
            Direction.South -> result.swap(item, frontier.first({ e -> e.direction == Direction.South }).value)
            Direction.West -> result.swap(item, frontier.first({ e -> e.direction == Direction.West }).value)
            Direction.East -> result.swap(item, frontier.first({ e -> e.direction == Direction.East }).value)
            else -> IllegalArgumentException("Can't move by none")
        }
    }

    fun getResult(): Array<T> {
        return result
    }

    override fun toString(): String {
        return "Moved $direction, Result ${result.contentToString()}"
    }

    override fun hashCode(): Int {
        return result.graphHash()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Move<*>

        if (direction != other.direction) return false
        if (!Arrays.equals(result, other.result)) return false

        return true
    }
}