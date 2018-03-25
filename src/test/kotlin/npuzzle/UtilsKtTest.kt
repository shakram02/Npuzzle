package npuzzle

import org.junit.Assert.*
import org.junit.Test

class UtilsKtTest {
    @Test
    fun indexToPair() {
        val array = arrayOf(1, 2, 3, 4)

        assertEquals(Pair(0, 0), array.indexToPair(0))
        assertEquals(Pair(0, 1), array.indexToPair(1))
        assertEquals(Pair(1, 0), array.indexToPair(2))
        assertEquals(Pair(1, 1), array.indexToPair(3))
    }

    @Test
    fun manhattanDistanceTest() {
        val array = Array(10, { i -> i })   // No shifts
        assertEquals(0, array.calculateCost({ x, y -> manhattanDistance(x, y) }))

        val misplacedRow = arrayOf(1, 2, 3)
        // Every item is shifted 1 cell in the X direction
        assertEquals(3, misplacedRow.calculateCost({ x, y -> manhattanDistance(x, y) }))

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
        val misplacedCols = arrayOf(1, 2, 3, 4)
        assertEquals(6, misplacedCols.calculateCost({ x, y -> manhattanDistance(x, y) }))
    }

    @Test
    fun euclideanDistanceTest() {
        val array = Array(10, { i -> i })
        assertEquals(0, array.calculateCost({ x, y -> euclideanDistance(x, y) }))

        val misplacedRow = arrayOf(1, 2, 3) // Shifts in 1D, equivalent to manhattan
        assertEquals(3, misplacedRow.calculateCost({ x, y -> euclideanDistance(x, y) }))

        /*
           Truth:      Actual:
               0   1        1   2
               2   3        3   4

               Diagonals represent an error by 1 instead of 2 in manhattan distance
        */
        val misplacedCols = arrayOf(1, 2, 3, 4)
        assertEquals(4, misplacedCols.calculateCost({ x, y -> euclideanDistance(x, y) }))
    }
}