package tree;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * Ein Iterator, der einen Binärbaum (Tree) in In-Order-Reihenfolge durchläuft.
 * @param <T> Der Typ der Elemente im Baum (muss Comparable sein).
 */
public class TreeIterator<T extends Comparable<T>> implements Iterator<T> {

    private final Stack<Tree<T>> stack = new Stack<>();

    /**
     * Konstruktor: Initialisiert den Iterator mit einem Baum.
     * @param root Die Wurzel des Baumes
     */
    public TreeIterator(Tree<T> root) {
        pushLeft(root);
    }

    /**
     * Speichert alle linken Kinder des gegebenen Baumes im Stack.
     */
    private void pushLeft(Tree<T> tree) {
        while (!tree.isEmpty()) {
            stack.push(tree);
            tree = tree.leftChild();
        }
    }

    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        Tree<T> node = stack.pop();
        T result = node.data();
        pushLeft(node.rightChild());
        return result;
    }
}
