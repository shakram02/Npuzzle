@file:JvmName("Main")

package npuzzle

import npuzzle.abstractions.Direction
import npuzzle.abstractions.Move
import npuzzle.abstractions.State
import java.util.*

fun main(args: Array<String>) {
    val graph = arrayOf(1, 2, 5, 3, 4, 0, 6, 7, 8)
//    val graph =arrayOf(1, 4, 2, 6, 5, 8, 7, 3, 0)
//    graph.swap(2, 3)
//    println(graph.contentToString())

//    println(Move(graph, 0, Direction.North))
//    val state = State(graph, Move(graph, 0, Direction.None))
//    println(state.move(0, Direction.North).move)

    val initialState = State(0, graph, Move(graph, 0, Direction.None))

//    val sameState = State(graph, Move(graph, 0, Direction.None))
//    val set = HashSet(mutableListOf(initialState))
//    println(set.contains(sameState))

//    println(frontier)
    val finalState = arrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8)
    val path = bfs(initialState, { x -> Arrays.equals(finalState, x.move.getResult()) })
    println(path)

    // TODO, check this condition
    val pathDfs = dfs(initialState, { x -> Arrays.equals(finalState, x.move.getResult()) })
    println(pathDfs)

}

