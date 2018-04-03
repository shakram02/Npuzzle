package aipack.abstractions

interface CostCalculator <T>{
    fun calculateCost(succesorable: Succesorable<T>): Int
}