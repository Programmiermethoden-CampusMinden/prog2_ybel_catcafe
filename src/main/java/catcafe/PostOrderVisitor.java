package catcafe;

import tree.Empty;
import tree.Node;
import tree.TreeVisitor;

public class PostOrderVisitor<T extends Comparable<T>> implements TreeVisitor<T> {
    @Override
    public String visit(Empty<T> node) {
        return "";
    }

    @Override
    public String visit(Node<T> node) {
        return node.leftChild().accept(this)
             + node.rightChild().accept(this)
             + node.data().toString() + "\n";
    }
}