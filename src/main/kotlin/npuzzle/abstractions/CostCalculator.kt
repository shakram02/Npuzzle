package npuzzle.abstractions

interface CostCalculator <T>{
    fun calculateCost(succesorable: Succesorable<T>): Int
}