package npuzzle

import npuzzle.abstractions.Direction
import npuzzle.abstractions.FrontierItem
import npuzzle.abstractions.State
import npuzzle.datastructures.Queue


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
