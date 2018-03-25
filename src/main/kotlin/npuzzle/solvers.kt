package npuzzle

import npuzzle.abstractions.State
import npuzzle.datastructures.Queue
import npuzzle.datastructures.Stack
import java.util.*
import kotlin.collections.HashSet

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
    val possibleTargets = Queue(initialState.move.getResult().toMutableList())
    val visitedTargets = HashSet<T>()
    visitedTargets.add(possibleTargets.dequeue()!!)

    while (frontier.isNotEmpty()) {
        val state = frontier.pop()!!
        explored.add(state)

        if (isAtGoal(state)) {
            return getSolutionPath(state)
        }

        for (neighbor in state.getFrontier()) {
            if (explored.contains(neighbor) || frontier.contains(neighbor)) {
                continue
            }
            frontier.push(neighbor)
        }

    }

    return mutableListOf()
}

fun <T : Number> aStar(initialState: State<T>, isAtGoal: (State<T>) -> Boolean,
                       costFunction: (Pair<Int, Int>, Pair<Int, Int>) -> Int): Collection<State<T>> {
    val frontier: PriorityQueue<State<T>> = PriorityQueue(StateComparator(costFunction))
    frontier.add(initialState)
    val explored = HashSet<State<T>>()

    while (frontier.isNotEmpty()) {
        val state = frontier.remove()
        explored.add(state)

        if (isAtGoal(state)) {
            return getSolutionPath(state)
        }

        for (neighbor in state.getFrontier()) {
            if (explored.contains(neighbor) || frontier.contains(neighbor)) {
                continue
            }

            frontier.add(neighbor)
        }
    }

    return mutableListOf()
}
