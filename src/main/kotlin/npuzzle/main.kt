@file:JvmName("Main")

package npuzzle

import java.util.*

fun main(args: Array<String>) {
    val graph = mutableListOf(1, 2, 5, 3, 4, 0, 6, 7, 8).toTypedArray()
//    graph.swap(2, 3)
//    println(graph.contentToString())

    Move(graph, 0, Direction.North)
    println(graph.contentToString())
}

enum class Direction {
    North,
    South,
    West,
    East
}

class Move<out T>(graph: Array<T>, val item: T, val direction: Direction) {
    init {
        val frontier = graph.getFrontier(item)
        when (direction) {
            Direction.North -> graph.swap(item, frontier.first({ e -> e.direction == Direction.North }).value)
            Direction.South -> graph.swap(item, frontier.first({ e -> e.direction == Direction.South }).value)
            Direction.West -> graph.swap(item, frontier.first({ e -> e.direction == Direction.West }).value)
            Direction.East -> graph.swap(item, frontier.first({ e -> e.direction == Direction.East }).value)
        }
    }
}

data class State<T>(private val graph: Array<T>, val moves: List<Move<T>>) {
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

data class FrontierItem<out T>(val value: T, val direction: Direction) {
    override fun toString(): String {
        return "$value <$direction>"
    }
}
