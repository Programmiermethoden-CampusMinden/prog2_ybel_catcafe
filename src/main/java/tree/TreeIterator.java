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
    //basiert auf einem Stack
    private final Stack<Tree<T>> stack;

    /**
     * Create a new Iterator for a given tree.
     *
     * @param root top-level node of the tree
     */

    public TreeIterator(Tree<T> root) {
        requireNonNull(root);

        stack = new Stack<>();
        //immer zur tiefsten Node und push diese in stack
        pushAllLeftNodes(root);
    }

    //gibt aus ob es nachfolger gibt
    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }
    //geht durch alle elemente durch
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
        //sobald root null ist abbruch sonst hole die sachen ins stack
        while (!node.isEmpty()) {
            stack.push(node);
            node = node.leftChild();
        }
    }
}
