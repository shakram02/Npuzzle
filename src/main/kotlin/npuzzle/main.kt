@file:JvmName("Main")

package npuzzle

import java.lang.IllegalArgumentException
import java.util.*

fun main(args: Array<String>) {
//    val graph = mutableListOf(1, 2, 5, 3, 4, 0, 6, 7, 8).toTypedArray()
    val graph = mutableListOf(1, 4, 2, 6, 5, 8, 7, 3, 0).toTypedArray()
//    graph.swap(2, 3)
//    println(graph.contentToString())

//    println(Move(graph, 0, Direction.North))
//    val state = State(graph, Move(graph, 0, Direction.None))
//    println(state.move(0, Direction.North).move)

    val initialState = State(0, graph, Move(graph, 0, Direction.None))

//    val sameState = State(graph, Move(graph, 0, Direction.None))
//    val set = HashSet(mutableListOf(initialState))
//    println(set.contains(sameState))

//    println(frontier)
    val finalState = Array(9, { i -> i })
    val path = bfs(initialState, { x -> Arrays.equals(finalState, x.move.getResult()) })
    println(path)

}

enum class Direction {
    North,
    South,
    West,
    East,
    None
}


class State<T : Number>(private val item: T, private val graph: Array<T>, val move: Move<T>, val parent: State<T>? = null) {
    fun move(direction: Direction): State<T> {
        val move = Move(graph, item, direction)
        return State(item, move.getResult(), move, this)
    }

    // Generate states of moves in all possible directions
    fun getFrontier(): List<State<T>> {
        val result = mutableListOf<State<T>>()

        for (item in graph.getFrontier(item)) {
            result.add(this.move(item.direction))
        }
        return result
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as State<*>

        return this.graph.graphHash() == other.graph.graphHash()
    }


    override fun toString(): String {
        val sideLen = Math.sqrt(graph.size.toDouble()).toInt()
        val builder = StringBuilder()

        for (i in 0 until graph.size) {
            if (i % sideLen == 0) {
                builder.append("\n")
            }
            builder.append(graph[i])
            builder.append("\t")
        }
        return builder.toString()
    }

    override fun hashCode(): Int {
        return graph.graphHash()
    }
}

fun <T> Array<T>.swap(firstItem: T, secondItem: T) {
    val firstIndex = this.indexOf(firstItem)
    val secondIndex = this.indexOf(secondItem)

    val tmp = this[firstIndex]
    this[firstIndex] = this[secondIndex]
    this[secondIndex] = tmp
}

fun <T> Array<T>.getFrontier(item: T): List<FrontierItem<T>> {
    val width = Math.sqrt(size.toDouble()).toInt()
    val neighbors = mutableListOf<FrontierItem<T>>()
    val index = this.indexOf(item)

    // The row above me isn't a boundary (North)
    if (index - width >= 0) {
        neighbors.add(FrontierItem(this[index - width], Direction.North))
    }

    // The row below me isn't a boundary (South)
    if (index + width < size) {
        neighbors.add(FrontierItem(this[index + width], Direction.South))
    }

    // I'm not on the boundary (West)
    if (index % width != 0) {
        neighbors.add(FrontierItem(this[index - 1], Direction.West))
    }

    // My right neighbour isn't a boundary (East)
    if ((index + 1) % width != 0) {
        neighbors.add(FrontierItem(this[index + 1], Direction.East))
    }

    return neighbors
}

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

data class FrontierItem<out T>(val value: T, val direction: Direction) {
    override fun toString(): String {
        return "$value <$direction>"
    }
}

fun <T> Array<T>.graphHash(): Int {
    return this.joinToString("").toInt()
}
