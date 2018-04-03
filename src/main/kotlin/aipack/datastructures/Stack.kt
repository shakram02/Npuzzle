package aipack.datastructures

/**
 * Created by gazollajunior on 03/04/16.
 * SOURCE: https://github.com/gazolla/Kotlin-Algorithm/blob/master/Stack/Stack.kt
 */


class Stack<T>(list: MutableList<T>) : Iterator<T> {

    var itCounter: Int = 0

    var items: MutableList<T> = list


    fun isEmpty(): Boolean = this.items.isEmpty()

    fun count(): Int = this.items.count()

    fun push(element: T) {
        val position = this.count()
        this.items.add(position, element)
    }

    override fun toString() = this.items.toString()

    fun pop(): T? {
        return if (this.isEmpty()) {
            null
        } else {
            val item = this.items.count() - 1
            this.items.removeAt(item)
        }
    }

    fun peek(): T? {
        if (isEmpty()) {
            return null
        } else {
            return this.items[this.items.count() - 1]
        }
    }
    
    override fun hasNext(): Boolean {
        val hasNext = itCounter < count()

        // As soon as condition fails, reset the counter
        if (!hasNext) itCounter = 0

        return hasNext
    }

    override fun next(): T {
        if (hasNext()) {
            val topPos: Int = (count() - 1) - itCounter
            itCounter++
            return this.items[topPos]
        } else {
            throw NoSuchElementException("No such element")
        }
    }

    fun isNotEmpty(): Boolean {
        return !this.isEmpty()
    }

    fun contains(neighbor: T): Boolean {
        return items.contains(neighbor)
    }

}
