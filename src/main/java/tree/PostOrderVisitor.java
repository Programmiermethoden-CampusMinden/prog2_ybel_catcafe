package tree;

/**
 * Visitor that traverses a binary search tree postorder.
 *
 * @param <T> type of the elements in the tree
 */
public class PostOrderVisitor<T extends Comparable<T>> implements TreeVisitor<T> {

    @Override
    public String visit(Empty<T> node) {
        return "";
    }

    @Override
    public String visit(Node<T> node) {
        // links -> rechts -> daten
        String left = node.leftChild().accept(this);
        String right = node.rightChild().accept(this);
        String data = node.data().toString();

        return left + right + data;
    }
}
