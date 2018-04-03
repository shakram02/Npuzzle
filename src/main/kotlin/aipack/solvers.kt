package aipack

import aipack.abstractions.CostCalculator
import aipack.abstractions.State
import aipack.datastructures.Queue
import aipack.datastructures.Stack
import java.util.*
import kotlin.collections.HashSet

fun <T : Number> bfs(initialState: State<T>, isAtGoal: (State<T>) -> Boolean, maxIterations: Int = 1000): Collection<State<T>> {
    val frontier = Queue(mutableListOf(initialState))
    val explored = HashSet<State<T>>()
    var iterCount = 0

    while (frontier.isNotEmpty()) {
        if (iterCount++ > maxIterations) {
            throw IllegalStateException("Failed to find a solution after $iterCount iterations")
        }

        val state = frontier.dequeue()!!
        explored.add(state)

        if (isAtGoal(state)) {
            return getSolutionPath(state)
        }

        // Calculate all possible unexplored states from this one
        for (neighbor in state.getSuccessors()) {
            if (explored.contains(neighbor) || frontier.contains(neighbor)) {
                continue
            }
            frontier.enqueue(neighbor)
        }
    }

    return mutableListOf()
}

fun <T : Number> dfs(initialState: State<T>, isAtGoal: (State<T>) -> Boolean, maxIterations: Int = 1000): Collection<State<T>> {
    val frontier = Stack(mutableListOf(initialState))
    val explored = HashSet<State<T>>()
    var iterCount = 0


    while (frontier.isNotEmpty()) {
        if (iterCount++ > maxIterations) {
            throw IllegalStateException("Failed to find a solution after $iterCount iterations")
        }

        val state = frontier.pop()!!
        explored.add(state)

        if (isAtGoal(state)) {
            return getSolutionPath(state)
        }

        for (neighbor in state.getSuccessors()) {
            if (explored.contains(neighbor) || frontier.contains(neighbor)) {
                continue
            }
            frontier.push(neighbor)
        }

    }

    return mutableListOf()
}

fun <T : Number> aStar(initialState: State<T>, isAtGoal: (State<T>) -> Boolean, costCalculator: CostCalculator<T>,
                       maxIterations: Int = 1000): Collection<State<T>> {
    val frontier: PriorityQueue<State<T>> = PriorityQueue(StateComparator(costCalculator))
    frontier.add(initialState)
    val explored = HashSet<State<T>>()
    var iterCount = 0

    while (frontier.isNotEmpty()) {
        if (iterCount++ > maxIterations) {
            throw IllegalStateException("Failed to find a solution after $iterCount iterations")
        }

        val state = frontier.remove()
        explored.add(state)

        if (isAtGoal(state)) {
            return getSolutionPath(state)
        }

        for (neighbor in state.getSuccessors()) {
            if (explored.contains(neighbor) || frontier.contains(neighbor)) {
                continue
            }

            frontier.add(neighbor)
        }
    }

    return mutableListOf()
}
