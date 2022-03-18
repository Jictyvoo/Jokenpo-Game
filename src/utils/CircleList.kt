package utils

class CircleList<T> {
    /*Node class*/
    private inner class NodeList<K>(private var data: K) {
        var previous: NodeList<K>? = null
        var next: NodeList<K>? = null

        fun setInformation(received: K) {
            this.data = received
        }

        fun getData(): K {
            return this.data
        }
    }

    private var current: NodeList<T>? = null
    private var first: NodeList<T>?
    private var last: NodeList<T>?

    /*Constructor*/
    constructor() {
        /*this.current = new NodeList();*/
        first = current
        last = current
        current?.next = current
        current?.previous = current
    }

    constructor(received: T) {
        current = NodeList(received)
        first = current
        last = current
        current?.next = current
        current?.previous = current
    }

    fun setInformation(received: T): Boolean {
        if (current == null) return false
        current?.setInformation(received)
        return true
    }

    val data: T?
        get() = current?.getData()

    operator fun next(): Boolean {
        if (current == null) return false
        current = current?.next
        return true
    }

    fun previous(): Boolean {
        if (current == null) return false
        current = current?.previous
        return true
    }

    fun inNext(): T? {
        return current?.next?.getData()
    }

    fun inPrevious(): T? {
        return current?.previous?.getData()
    }

    private fun makingNode(receivedNode: NodeList<T>) {
        if (current == null) {
            current = receivedNode
            first = current
            last = current
            current?.next = current
            current?.previous = current
        } else {
            receivedNode.next = current
            receivedNode.previous = current?.previous
            current?.previous?.next = receivedNode
            current?.previous = receivedNode
            last = receivedNode
            current = receivedNode
        }
    }

    fun add(received: T) {
        makingNode(NodeList(received))
    }

    fun remove(): Boolean {
        if (current == null) return false
        current?.previous?.next = current?.next
        current?.next?.previous = current?.previous
        if (current === first) {
            first = current?.next
            current = current?.next
        }
        if (current === last) {
            last = current?.previous
            current = current?.previous
        }
        if (current?.next === current) {
            current = null
            first = null
            last = null
        }
        return true
    }

    fun deleteList() {
        while (remove());
    }
}
