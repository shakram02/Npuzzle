@file:JvmName("Main")

package aipack

import aipack.abstractions.PuzzleArray
import aipack.abstractions.PuzzleCostCalculator
import aipack.abstractions.State
import npuzzle.abstractions.*
import java.util.*

fun main(args: Array<String>) {
    val graph = arrayOf(1, 2, 5, 3, 0, 4, 6, 7, 8)    // Easy case
//    val graph = arrayOf(1, 4, 2, 6, 5, 8, 7, 3, 0)  // Hard case
    val finalGraph = arrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8)

    val manhattanCalculator = PuzzleCostCalculator({ x, y -> manhattanDistance(x, y) })
    val euclideanCalculator = PuzzleCostCalculator({ x, y -> euclideanDistance(x, y) })

    val initialState = State(PuzzleArray(0, graph))
    val finalState = State(PuzzleArray(0, finalGraph))

    printBanner("BFS")
    val path = bfs(initialState, { x -> finalState == x })
    println(path)

    printBanner("A* - Manhattan")
    val pathAStar = aStar(initialState, { x -> finalState == x }, manhattanCalculator)
    println(pathAStar)

    printBanner("A* - Euclidean")
    val pathAStarEuclidean = aStar(initialState, { x -> finalState == x }, euclideanCalculator)
    println(pathAStarEuclidean)


    printBanner("DFS")
    // DFS gets stuck! because of the huge number of cases
//    val pathDfs = dfs(initialState, { x -> finalState == x })
//    println(pathDfs)
}

fun printBanner(algorithmName: String) {
    println("\n$algorithmName\n")
    println("-".repeat(12))
}
