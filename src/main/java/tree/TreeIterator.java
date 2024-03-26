package tree;

import static java.util.Objects.requireNonNull;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * Iterator for our binary search trees {@link Tree}.
 *
 * @param <T> parametric type of the node data
 */
public class TreeIterator<T extends Comparable<T>> implements Iterator<T> {
    private final Stack<Tree<T>> stack;

    /**
     * Create a new Iterator for a given tree.
     *
     * @param root top-level node of the tree
     */
    public TreeIterator(Tree<T> root) {
        requireNonNull(root);

        stack = new Stack<>();
        pushAllLeftNodes(root);
    }

    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    @Override
    public T next() {
        if (hasNext()) {
            Tree<T> node = stack.pop();
            pushAllLeftNodes(node.rightChild());
            return node.data();
        } else {
            throw new NoSuchElementException();
        }
    }

    private void pushAllLeftNodes(Tree<T> node) {
        requireNonNull(node);

        while (!node.isEmpty()) {
            stack.push(node);
            node = node.leftChild();
        }
    }
}
