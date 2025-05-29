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
// ANALYSE
public record Node<T extends Comparable<T>>(Tree<T> leftChild, T data, Tree<T> rightChild)
        implements Tree<T> {
    /** Create a new node: Ensure that all arguments are not {@code null}. */
    //Node wird nur erstellt wenn data left und right Child nicht NULL sind
    public Node {
        requireNonNull(data);
        requireNonNull(leftChild);
        requireNonNull(rightChild);
    }
    //Prüfen ob Leer wenn ja gib false zurück
    @Override
    public boolean isEmpty() {
        return false;
    }

    //Jeder eintrag im baum braucht UMBEDINGT einen Wert (data)
    @Override
    public Tree<T> addData(T data) {
        requireNonNull(data);
        // this.data = Vorherige wert
        // compareTo(data) = übergebene wert
        //compareVal ist dann -1 0 oder +1 je nachdem ob kleienr gleich oder Größer
        int compareVal = this.data.compareTo(data);
        if (compareVal < 0) {
            // this.data < data: insert into rightChild subtree
            return new Node<>(leftChild, this.data, rightChild.addData(data));
        } else if (compareVal > 0) {
            // this.data > data: insert into leftChild subtree
            return new Node<>(leftChild.addData(data), this.data, rightChild);
        } else {
            //HIER GEHEN KATZEN VERLOREN!!!!!!!!!!
            // this.data == data: do nothing
            return this;
        }
    }
    // quasi eine art Eintritt wenn der visitor nicht Null ist!
    @Override
    public String accept(TreeVisitor<T> visitor) {
        requireNonNull(visitor);

        return visitor.visit(this);
    }
    //ermöglicht es die klasse in einer for each schleife zu verwenden
    @Override
    public Iterator<T> iterator() {
        return new TreeIterator<>(this);
    }
    // nutzt iterator und accept um durch alle mit wert durchzulaufen
    @Override
    public void forEach(Consumer<? super T> action) {
        requireNonNull(action);

        for (T t : this) {
            action.accept(t);
        }
    }
    //ermöglicht die Nutzung von Streams über die Baumstruktur nutzt auch den iterator und gibt einen *geordneten* Spliterator zurück
    @Override
    public Spliterator<T> spliterator() {
        return Spliterators.spliteratorUnknownSize(iterator(), Spliterator.ORDERED);
    }
}
