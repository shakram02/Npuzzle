package npuzzle

import npuzzle.datastructures.Queue

fun <T : Number> bfs(initialState: State<T>, isAtGoal: (State<T>) -> Boolean): Queue<State<T>> {
    val frontier = Queue(mutableListOf(initialState))
    val explored = HashSet<State<T>>()


    while (frontier.isNotEmpty()) {
        val state = frontier.dequeue()!!
        explored.add(state)

        if (isAtGoal(state)) {
            return getSolutionPath(state)
        }

        // Calculate all possible unexplored states from this one
        for (neighbor in state.getFrontier()) {
            if (explored.contains(neighbor) || frontier.contains(neighbor)) {
                continue
            }
            frontier.enqueue(neighbor)
        }
    }

    return Queue(mutableListOf())
}

private fun <T : Number> getSolutionPath(state: State<T>): Queue<State<T>> {
    val path = Queue(mutableListOf<State<T>>())
    var current: State<T>? = state

    // Backtrack
    while (current != null) {
        path.enqueue(current)
        current = current.parent
    }

    return path.reverse()
}
