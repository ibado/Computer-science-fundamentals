package datastructures

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class TreeNodeTest {

    @Test
    fun `find node n`() {
        val childToFind = TreeNode(6)
        val tree = TreeNode(3).apply {
            addChild(TreeNode(8))
            addChild(TreeNode(5).apply { addChild(childToFind) })
        }

        assertEquals(childToFind, tree.find(6))
    }

    @Test
    fun `find nonexistent child return null`() {
        val tree = TreeNode(3).apply {
            addChild(TreeNode(5))
            addChild(TreeNode(3))
        }

        assertEquals(null, tree.find(6))
    }

    @Test
    fun `is parent return true`() {
        val child = TreeNode(6)
        val tree = TreeNode(3).apply {
            addChild(TreeNode(8))
            addChild(TreeNode(5).apply { addChild(child) })
        }

        assertTrue(tree.isParentOf(child))
    }

    @Test
    fun `is parent return false`() {
        val child = TreeNode(6)
        val tree = TreeNode(3).apply {
            addChild(TreeNode(8))
            addChild(TreeNode(5).apply { addChild(child) })
        }

        assertFalse(child.isParentOf(tree))
    }

    @Test
    fun `remove child`() {
        val childToRemove = TreeNode(91)
        val tree = TreeNode(3).apply {
            addChild(TreeNode(8))
            addChild(childToRemove)
        }

        tree.removeChild(childToRemove)

        assertEquals(null, tree.find(childToRemove.value))
    }

    @Test
    fun `remove nonexistent child do nothing`() {
        val tree = TreeNode(3).apply {
            addChild(TreeNode(8))
            addChild(TreeNode(5).apply { addChild(TreeNode(91)) })
        }

        val children = tree.children()
        tree.removeChild(TreeNode(51))

        assertEquals(children, tree.children())
    }
}