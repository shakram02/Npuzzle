package npuzzle.datastructures

/**
 * Created by gazollajunior on 05/04/16.
 * SOURCE: https://github.com/gazolla/Kotlin-Algorithm/blob/master/Queue/Queue.kt
 */
class Queue<T>(list: MutableList<T>) : Iterable<T> {
    /**
     * Returns an iterator over the elements of this object.
     */
    override fun iterator(): Iterator<T> {
        return items.iterator()
    }

    var items: MutableList<T> = list

    fun isEmpty(): Boolean = this.items.isEmpty()
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

    fun contains(item: T): Boolean {
        return this.items.contains(item)
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


    fun reverse(): Queue<T> {
        val queue = Queue(mutableListOf<T>())
        val copy = items.toMutableList()
        copy.reverse()
        queue.addAll(copy)
        return queue
    }
}


fun main(args: Array<String>) {

    var initialValue = mutableListOf<Int>(10)
    var queue = Queue<Int>(initialValue)
    println(queue)
    queue.enqueue(22)
    println(queue)
    queue.enqueue(55)
    println(queue)
    queue.enqueue(77)
    println(queue)
    queue.dequeue()
    println(queue)
    queue.dequeue()
    println(queue)
    queue.dequeue()
    println(queue)

    // Iterating over queue
    for (item in queue) println("Item in queue : " + item)

}