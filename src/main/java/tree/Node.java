package tree;

import static java.util.Objects.requireNonNull;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;

/**
 * Node in a binary search tree.
 *
 * @param data vehicle to store in the new node (must not be {@code null})
 * @param leftChild leftChild subtree
 * @param rightChild rightChild subtree
 * @param <T> parametric type of the node data
 */
public record Node<T extends Comparable<T>>(Tree<T> leftChild, T data, Tree<T> rightChild)
    implements Tree<T> {
    /** Create a new node: Ensure that all arguments are not {@code null}. */
    public Node {
        requireNonNull(data);
        requireNonNull(leftChild);
        requireNonNull(rightChild);
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public Tree<T> addData(T data) {
        requireNonNull(data);

        int compareVal = this.data.compareTo(data);
        if (compareVal < 0) {
            // this.data < data: insert into rightChild subtree
            return new Node<>(leftChild, this.data, rightChild.addData(data));
        } else if (compareVal > 0) {
            // this.data > data: insert into leftChild subtree
            return new Node<>(leftChild.addData(data), this.data, rightChild);
        } else {
            // this.data == data: do nothing
            return this;
        }
    }

    @Override
    public String accept(TreeVisitor<T> visitor) {
        requireNonNull(visitor);

        return visitor.visit(this);
    }

    @Override
    public Iterator<T> iterator() {
        return new TreeIterator<>(this);
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        requireNonNull(action);

        for (T t : this) {
            action.accept(t);
        }
    }

    @Override
    public Spliterator<T> spliterator() {
        return Spliterators.spliteratorUnknownSize(iterator(), Spliterator.ORDERED);
    }
}
