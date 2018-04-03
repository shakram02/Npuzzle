package aipack

import aipack.abstractions.CostCalculator
import aipack.abstractions.State
import aipack.datastructures.Queue
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


class StateComparator<T : Number>(private val costCalculator: CostCalculator<T>) : Comparator<State<T>> {

    override fun compare(first: State<T>?, second: State<T>?): Int {
        val first = costCalculator.calculateCost(first!!.stateRepresentation)
        val second = costCalculator.calculateCost(second!!.stateRepresentation)
        return first.compareTo(second)
    }
}
