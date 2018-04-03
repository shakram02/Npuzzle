package npuzzle.abstractions

class PuzzleCostCalculator(private val costFunction: (Pair<Int, Int>, Pair<Int, Int>) -> Int) : CostCalculator<Int> {
    override fun calculateCost(succesorable: Succesorable<Int>): Int {
        return calculateCost(succesorable as PuzzleArray<Int>)
    }

    private fun calculateCost(succesorable: PuzzleArray<Int>): Int {
        var weight = 0

        for ((index, item) in succesorable.withIndex()) {

            val (x, y) = succesorable.indexToPair(index)
            val (trueX, trueY) = succesorable.indexToPair(item)

            weight += costFunction(Pair(x, y), Pair(trueX, trueY))
        }

        return weight
    }
}