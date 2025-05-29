package tree;

import static java.util.Objects.requireNonNull;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;

/**
 * Empty node in a binary search tree.
 *
 * @param <T> parametric type of the node data
 */
public record Empty<T extends Comparable<T>>() implements Tree<T> {
    //gibt true zurück wenn leer
    @Override
    public boolean isEmpty() {
        return true;
    }
    //wenn data nicht NULL ist wird eine neue node erstellt (am ende) vom tree mit 2 Leeren "kindern"
    @Override
    public Tree<T> addData(T data) {
        requireNonNull(data);

        return new Node<>(new Empty<>(), data, new Empty<>());
    }
    //checkt sich selbst
    @Override
    public String accept(TreeVisitor<T> visitor) {
        requireNonNull(visitor);

        return visitor.visit(this);
    }
    //iterator für spätere methode
    @Override
    public Iterator<T> iterator() {
        return new TreeIterator<>(this);
    }
    //for each nutzt iterator geht durch alle durch
    @Override
    public void forEach(Consumer<? super T> action) {
        requireNonNull(action);

        for (T t : this) {
            action.accept(t);
        }
    }
    //spliterator kann teile vom baum nehmen (geordnet) und ermöglicht die nutzung von Stream in der Baumstruktur
    @Override
    public Spliterator<T> spliterator() {
        return Spliterators.spliteratorUnknownSize(iterator(), Spliterator.ORDERED);
    }
}
