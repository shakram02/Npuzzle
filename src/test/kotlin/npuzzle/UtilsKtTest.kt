package npuzzle

import npuzzle.abstractions.PuzzleArray
import npuzzle.abstractions.PuzzleCostCalculator
import org.junit.Assert.*
import org.junit.Test

class UtilsKtTest {
    @Test
    fun indexToPair() {
        val array = arrayOf(1, 2, 3, 4)
        val puzzle = PuzzleArray(0, array)
        assertEquals(Pair(0, 0), puzzle.indexToPair(0))
        assertEquals(Pair(0, 1), puzzle.indexToPair(1))
        assertEquals(Pair(1, 0), puzzle.indexToPair(2))
        assertEquals(Pair(1, 1), puzzle.indexToPair(3))
    }

    @Test
    fun manhattanDistanceTest() {
        val array = PuzzleArray(0, Array(10, { i -> i }))   // No shifts
        val manhattanCalculator = PuzzleCostCalculator({ x, y -> manhattanDistance(x, y) })
        assertEquals(0, manhattanCalculator.calculateCost(array))

        val misplacedRow = PuzzleArray(0, arrayOf(1, 2, 3))
        // Every item is shifted 1 cell in the X direction
        assertEquals(3, manhattanCalculator.calculateCost(misplacedRow))

        /*
            Truth:      Actual:
               0   1        1   2
               2   3        3   4
            Pairs:
            Distance(Actual, Truth):
               1: (0, 0) - (0, 1)   => 1
               2: (0, 1) - (1, 0)   => 2
               3: (1, 0) - (1, 1)   => 1
               4: (1, 1) - (2, 0)   => 2
               Error: 6
         */
        val misplacedCols = PuzzleArray(0, arrayOf(1, 2, 3, 4))
        assertEquals(6, manhattanCalculator.calculateCost(misplacedCols))
    }

    @Test
    fun euclideanDistanceTest() {
        val array = PuzzleArray(0, Array(10, { i -> i }))
        val euclideanCalculator = PuzzleCostCalculator({ x, y -> euclideanDistance(x, y) })
        assertEquals(0, euclideanCalculator.calculateCost(array))

        val misplacedRow = PuzzleArray(0, arrayOf(1, 2, 3)) // Shifts in 1D, equivalent to manhattan
        assertEquals(3, euclideanCalculator.calculateCost(misplacedRow))

        /*
           Truth:      Actual:
               0   1        1   2
               2   3        3   4

               Diagonals represent an error by 1 instead of 2 in manhattan distance
        */
        val misplacedCols = PuzzleArray(0, arrayOf(1, 2, 3, 4))
        assertEquals(4, euclideanCalculator.calculateCost(misplacedCols))
    }
}