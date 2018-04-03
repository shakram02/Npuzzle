package aipack.abstractions

interface Succesorable<T> : Iterator<T> {
    fun getSuccessors(): HashMap<AbstractDirection, T>
    fun calculateCost(costCalculator: CostCalculator<T>): Int
    fun copyOf(): Succesorable<T>
    override fun toString(): String
    override fun hashCode(): Int
}