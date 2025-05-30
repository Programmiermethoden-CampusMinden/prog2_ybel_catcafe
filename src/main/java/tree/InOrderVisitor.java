package tree;

/**
 * Visitor that traverses a binary search tree inorder.
 *
 * @param <T> type of the elements in the tree
 */
public class InOrderVisitor<T extends Comparable<T>> implements TreeVisitor<T> {

    @Override
    public String visit(Empty<T> node) {
        return "";
    }

    @Override
    public String visit(Node<T> node) {
        // links -> daten -> rechts
        String left = node.leftChild().accept(this);
        String data = node.data().toString();
        String right = node.rightChild().accept(this);

        return left + data + right;
    }
}
