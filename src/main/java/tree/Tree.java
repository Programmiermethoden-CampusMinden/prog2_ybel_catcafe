package tree;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Interface of a sorted binary search tree.
 *
 * @param <T> parametric type of the node data
 */
public interface Tree<T extends Comparable<T>> extends Iterable<T> {
    /**
     * Add new data to the sorted tree.
     *
     * <p>The new object will be inserted as new leaf into the correct position in the tree. Adding
     * an already stored object does not change the tree.
     *
     * @param data Object to be inserted (must not be {@code null})
     * @return the new root node
     * @throws NullPointerException if data is {@code null}
     */
    Tree<T> addData(T data);

    /**
     * Access the stored data in the top-level node of this tree.
     *
     * @return data stored
     */
    default T data() {
        throw new UnsupportedOperationException();
    }

    /**
     * Return a copy of the leftChild child node of this node.
     *
     * @return leftChild child node of this node
     */
    default Tree<T> leftChild() {
        throw new UnsupportedOperationException();
    }

    /**
     * Return a copy of the rightChild child node of this node.
     *
     * @return rightChild child node of this node
     */
    default Tree<T> rightChild() {
        throw new UnsupportedOperationException();
    }

    /**
     * Is this an empty node?
     *
     * @return {@code true} iff not empty, {@code false} otherwise
     */
    boolean isEmpty();

    /**
     * How many non-empty nodes are stored in this tree?
     *
     * @return number of {@Link Node} elements
     */
    default long size() {
        return stream().count();
    }

    /**
     * Accept a visitor to this tree.
     *
     * <p>The visitor needs to do all the heavy lifting, i.e. it needs to implement not only how to
     * process the node's data but also how to traverse the children of this node/leaf.
     *
     * @param visitor The visitor, which will work on this node (must not be {@code null})
     * @return a string representation as result of the traversal process
     * @throws NullPointerException if visitor is {@code null}
     */
    String accept(TreeVisitor<T> visitor);

    /**
     * Create a stream to traverse the tree in depth-first order.
     *
     * @return stream of node data objects
     */
    default Stream<T> stream() {
        return StreamSupport.stream(spliterator(), false);
    }
}
