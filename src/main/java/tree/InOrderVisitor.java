package tree;

public class InOrderVisitor<T extends Comparable<T>> implements TreeVisitor<T> {

    @Override
    public String visit(Empty<T> empty) {
        return "";
    }

    @Override
    public String visit(Node<T> node) {
        String left = node.leftChild().accept(this);
        String value = node.data().toString();
        String right = node.rightChild().accept(this);

        return concat(left, value, right);
    }

    private String concat(String left, String value, String right) {
        StringBuilder sb = new StringBuilder();
        if (!left.isEmpty()) sb.append(left).append(" ");
        sb.append(value);
        if (!right.isEmpty()) sb.append(" ").append(right);
        return sb.toString();
    }
}
