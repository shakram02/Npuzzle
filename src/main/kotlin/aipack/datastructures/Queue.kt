package aipack.datastructures

/**
 * Created by gazollajunior on 05/04/16.
 * SOURCE: https://github.com/gazolla/Kotlin-Algorithm/blob/master/Queue/Queue.kt
 */
class Queue<T>(list: MutableList<T>) : Iterable<T>, Collection<T> {
    /**
     * Returns the size of the collection.
     */
    override val size: Int
        get() = items.size

    /**
     * Checks if all elements in the specified collection are contained in this collection.
     */
    override fun containsAll(elements: Collection<T>): Boolean {
        return items.containsAll(elements)
    }

    /**
     * Returns an iterator over the elements of this object.
     */
    override fun iterator(): Iterator<T> {
        return items.iterator()
    }

    var items: MutableList<T> = list

    override fun isEmpty(): Boolean = this.items.isEmpty()
    fun isNotEmpty(): Boolean = !this.isEmpty()

    fun count(): Int = this.items.count()

    override fun toString() = this.items.toString().replace(",", ",\n")

    fun enqueue(element: T) {
        this.items.add(element)
    }

    fun dequeue(): T? {
        return if (this.isEmpty()) {
            null
        } else {
            this.items.removeAt(0)
        }
    }

    override fun contains(element: T): Boolean {
        return this.items.contains(element)
    }

    fun peek(): T? {
        return this.items[0]
    }

    fun contentToString(): String {
        val builder = StringBuilder()

        for (item in items) {
            builder.appendln(item.toString())
        }

        return builder.toString()
    }

    fun addAll(elements: MutableList<T>) {
        this.items.addAll(elements)
    }

    fun addAll(elements: Iterable<T>) {
        this.items.addAll(elements)
    }


    fun reverse() {
        items.reverse()
    }
}
