@file:JvmName("Main")

package npuzzle

import java.util.*

fun main(args: Array<String>) {
    val graph = mutableListOf(1, 2, 5, 3, 4, 0, 6, 7, 8).toTypedArray()
//    graph.swap(2, 3)
//    println(graph.contentToString())

//    println(Move(graph, 0, Direction.North))
//    val state = State(graph, mutableListOf())
//    println(state.move(0, Direction.North).getMoves().last())


}

enum class Direction {
    North,
    South,
    West,
    East
}


data class State<T>(private val graph: Array<T>, private val moves: List<Move<T>>) {
    fun move(item: T, direction: Direction): State<T> {
        val move = Move(graph, item, direction)
        val moves = this.moves.toMutableList()
        moves.add(move)

        return State(move.getResult(), moves)
    }

    fun getMoves(): List<Move<T>> {
        // Protect from mutability by returning a copy
        return moves.toMutableList()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as State<*>

        if (!Arrays.equals(graph, other.graph)) return false

        return true
    }

    override fun hashCode(): Int {
        return Arrays.hashCode(graph)
    }
}

//fun <T> bfs(initialState: T, grid: Array<T>, isGoal: (T) -> Boolean) {
//    val frontier = ArrayDeque<FrontierItem<T>>(grid.getFrontier(initialState, grid, GRID_SIZE))
//    val visited = HashSet<T>()
//
//    while (frontier.isNotEmpty()) {
//        val state = frontier.pop()!!
//        visited.add(state)
//
//        if (isGoal(state)) {
//            return
//        }
//    }
//}

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
        }
    }

    fun getResult(): Array<T> {
        return result
    }

    override fun toString(): String {
        return "Moved $direction, Result ${result.contentToString()}"
    }
}

data class FrontierItem<out T>(val value: T, val direction: Direction) {
    override fun toString(): String {
        return "$value <$direction>"
    }
}
