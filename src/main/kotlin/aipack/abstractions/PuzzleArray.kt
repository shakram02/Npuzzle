package aipack.abstractions

import java.util.*

class PuzzleArray<T : Number>(private val item: T, val graph: Array<T>) : Succesorable<T> {
    private var iterator = graph.iterator()
    /**
     * Returns `true` if the iteration has more elements.
     */
    override fun hasNext(): Boolean {
        return if (iterator.hasNext()) {
            true
        } else {
            iterator = graph.iterator()
            false
        }
    }

    /**
     * Returns the next element in the iteration.
     */
    override fun next(): T {
        return iterator.next()
    }

    operator fun get(index: Int): T {
        return graph[index]
    }

    private fun <T> Array<T>.swap(firstItem: T, secondItem: T) {
        val firstIndex = this.indexOf(firstItem)
        val secondIndex = this.indexOf(secondItem)

        val tmp = this[firstIndex]
        this[firstIndex] = this[secondIndex]
        this[secondIndex] = tmp
    }

    private fun <T> Array<T>.getFrontier(item: T): HashMap<AbstractDirection, T> {
        val width = Math.sqrt(size.toDouble()).toInt()
        val neighbors = hashMapOf<AbstractDirection, T>()
        val index = this.indexOf(item)

        // The row above me isn't a boundary (North)
        if (index - width >= 0) {
            neighbors[Direction.North] = this[index - width]
        }

        // The row below me isn't a boundary (South)
        if (index + width < size) {
            neighbors[Direction.South] = this[index + width]
        }

        // I'm not on the boundary (West)
        if (index % width != 0) {
            neighbors[Direction.West] = this[index - 1]
        }

        // My right neighbour isn't a boundary (East)
        if ((index + 1) % width != 0) {
            neighbors[Direction.East] = this[index + 1]
        }

        return neighbors
    }

    private fun <T> Array<T>.graphHash(): Int {
        return this.joinToString("").toInt()
    }

    override fun copyOf(): Succesorable<T> {
        return PuzzleArray(item, graph.copyOf())
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PuzzleArray<*>

        if (!Arrays.equals(graph, other.graph)) return false

        return true
    }

    override fun hashCode(): Int {
        return graph.graphHash()
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

    override fun calculateCost(costCalculator: CostCalculator<T>): Int {
        return costCalculator.calculateCost(this)
    }

    override fun getSuccessors(): HashMap<AbstractDirection, T> {
        return graph.getFrontier(item)
    }

    fun move(direction: AbstractDirection): PuzzleArray<T> {
        val frontier = getSuccessors()
        val result = graph.copyOf()
        when (direction) {
            Direction.North -> result.swap(item, frontier[Direction.North]!!)
            Direction.South -> result.swap(item, frontier[Direction.South]!!)
            Direction.West -> result.swap(item, frontier[Direction.West]!!)
            Direction.East -> result.swap(item, frontier[Direction.East]!!)
            else -> IllegalArgumentException("Can't move by none")
        }

        return PuzzleArray(item, result)
    }

    fun indexToPair(index: Int): Pair<Int, Int> {
        val width = Math.sqrt(this.graph.size.toDouble()).toInt()

        val rowIndex = index / width
        val colIndex = index % width

        return Pair(rowIndex, colIndex)
    }

}