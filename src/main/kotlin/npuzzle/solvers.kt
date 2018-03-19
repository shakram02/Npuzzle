package npuzzle

import npuzzle.abstractions.State
import npuzzle.datastructures.Queue
import npuzzle.datastructures.Stack

fun <T : Number> bfs(initialState: State<T>, isAtGoal: (State<T>) -> Boolean): Collection<State<T>> {
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

    return mutableListOf()
}

fun <T : Number> dfs(initialState: State<T>, isAtGoal: (State<T>) -> Boolean): Collection<State<T>> {
    val frontier = Stack(mutableListOf(initialState))
    val explored = HashSet<State<T>>()

    while (frontier.isNotEmpty()) {
        val state = frontier.pop()!!
        explored.add(state)

        if (isAtGoal(state)) {
            return getSolutionPath(state)
        }

        for (neighbor in state.getFrontier().reversed()) {
            if (explored.contains(neighbor) || frontier.contains(neighbor)) {
                continue
            }
            frontier.push(neighbor)
        }
    }

    return mutableListOf()
}
