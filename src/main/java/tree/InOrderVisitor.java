package tree;

public class InOrderVisitor<T extends Comparable<T>> implements TreeVisitor<T> {

    @Override
    public String visit(Empty<T> node) {
        return "";
    }

    @Override
    public String visit(Node<T> node) {
        return node.leftChild().accept(this)
            + node.data().toString()
            + node.rightChild().accept(this);
    }
}
