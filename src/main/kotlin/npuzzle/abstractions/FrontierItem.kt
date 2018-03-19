package npuzzle.abstractions

data class FrontierItem<out T>(val value: T, val direction: Direction) {
    override fun toString(): String {
        return "$value <$direction>"
    }
}