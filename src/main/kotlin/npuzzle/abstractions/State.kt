package npuzzle.abstractions

import npuzzle.getFrontier
import npuzzle.graphHash

class State<T : Number>(private val item: T, val graph: Array<T>, val move: Move<T>, val parent: State<T>? = null) {
    private fun move(direction: Direction): State<T> {
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