package npuzzle

import npuzzle.abstractions.Direction
import npuzzle.abstractions.FrontierItem
import npuzzle.abstractions.State
import npuzzle.datastructures.Queue
import kotlin.math.roundToInt


fun <T : Number> getSolutionPath(state: State<T>): Collection<State<T>> {
    val path = Queue(mutableListOf<State<T>>())
    var current: State<T>? = state

    // Backtrack
    while (current != null) {
        path.enqueue(current)
        current = current.parent
    }
    path.reverse()

    return path
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

fun <T> Array<T>.graphHash(): Int {
    return this.joinToString("").toInt()
}

fun <T> Array<T>.indexToPair(index: Int): Pair<Int, Int> {
    val width = Math.sqrt(this.size.toDouble()).toInt()

    val rowIndex = index / width
    val colIndex = index % width

    return Pair(rowIndex, colIndex)
}

fun <T : Number> Array<out T>.calculateCost(costFunction: (Pair<Int, Int>, Pair<Int, Int>) -> Int): Int {
    var weight = 0

    for ((index, item) in this.withIndex()) {

        val (x, y) = this.indexToPair(index)
        val (trueX, trueY) = this.indexToPair(item.toInt())

        weight += costFunction(Pair(x, y), Pair(trueX, trueY))
    }

    return weight
}

fun manhattanDistance(p1: Pair<Int, Int>, p2: Pair<Int, Int>): Int {
    val (x1, y1) = p1
    val (x2, y2) = p2

    return Math.abs(x1 - x2) + Math.abs(y1 - y2)
}

fun euclideanDistance(p1: Pair<Int, Int>, p2: Pair<Int, Int>): Int {
    val (x1, y1) = p1
    val (x2, y2) = p2

    val wX = Math.pow((x1 - x2).toDouble(), 2.0)
    val wY = Math.pow((y1 - y2).toDouble(), 2.0)

    return Math.sqrt(wX + wY).roundToInt()
}


class StateComparator<T : Number>(private val costFunction: (Pair<Int, Int>, Pair<Int, Int>) -> Int) : Comparator<State<T>> {

    override fun compare(first: State<T>?, second: State<T>?): Int {
        return first!!.graph.calculateCost(costFunction).compareTo(second!!.graph.calculateCost(costFunction))
    }
}
