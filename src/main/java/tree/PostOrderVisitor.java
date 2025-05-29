package tree;

public class PostOrderVisitor<T extends Comparable<T>> implements TreeVisitor<T> {

    @Override
    public String visit(Empty<T> empty) {
        return "";
    }

    @Override
    public String visit(Node<T> node) {
        String left = node.leftChild().accept(this);
        String right = node.rightChild().accept(this);
        String value = node.data().toString();

        return concat(left, right, value);
    }

    private String concat(String left, String right, String value) {
        StringBuilder sb = new StringBuilder();
        if (!left.isEmpty()) sb.append(left).append(" ");
        if (!right.isEmpty()) sb.append(right).append(" ");
        sb.append(value);
        return sb.toString();
    }
}
