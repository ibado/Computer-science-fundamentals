package datastructures

data class TreeNode<T>(private var value_: T) {

    var value: T = value_
        private set

    private var children: MutableMap<T, TreeNode<T>> = mutableMapOf()

    fun addChild(node: TreeNode<T>) {
        children[node.value] = node
    }

    fun removeChild(node: TreeNode<T>) {
        children.remove(node.value)
    }

    fun isParentOf(node: TreeNode<T>): Boolean {
        if (children.isEmpty())
            return false
        else if (children.containsKey(node.value))
            return true
        else {
            children.forEach {
                if (it.value.isParentOf(node))
                    return true
            }
        }

        return false
    }

    fun children(): Set<TreeNode<T>> {
        return children.values.toSet()
    }

    fun find(nodeValue: T): TreeNode<T>? {
        if (value == nodeValue) return this
        else if (this.children.isNotEmpty()) {
            this.children[nodeValue]?.let { return it }
            this.children.values.forEach { child ->
                child.find(nodeValue)?.let { return it }
            }
        }

        return null
    }
}