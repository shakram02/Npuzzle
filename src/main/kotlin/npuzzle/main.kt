@file:JvmName("Main")

package npuzzle

import npuzzle.abstractions.Direction
import npuzzle.abstractions.Move
import npuzzle.abstractions.State
import java.util.*

fun main(args: Array<String>) {
    val graph = arrayOf(1, 2, 5, 3, 4, 0, 6, 7, 8)    // Easy case
//    val graph = arrayOf(1, 4, 2, 6, 5, 8, 7, 3, 0)  // Hard case

    val initialState = State(0, graph, Move(graph, 0, Direction.None))
    val finalState = arrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8)

    printBanner("BFS")
    val path = bfs(initialState, { x -> Arrays.equals(finalState, x.move.getResult()) })
    println(path)

    printBanner("A* - Manhattan")
    val pathAStar = aStar(initialState, { x -> Arrays.equals(finalState, x.move.getResult()) },
            { x, y -> manhattanDistance(x, y) })
    println(pathAStar)

    printBanner("A* - Euclidean")
    val pathAStarEuclidean = aStar(initialState, { x -> Arrays.equals(finalState, x.move.getResult()) },
            { x, y -> euclideanDistance(x, y) })
    println(pathAStarEuclidean)


    printBanner("DFS")
    // FIXME: DFS gets stuck!
    val pathDfs = dfs(initialState, { x -> Arrays.equals(finalState, x.move.getResult()) })
    println(pathDfs)
}

fun printBanner(algorithmName: String) {
    println("\n$algorithmName\n")
    println("-".repeat(12))
}
