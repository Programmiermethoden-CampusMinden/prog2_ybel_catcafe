package tree;

import catcafe.FelineOverLord;

public class PostOrderVisitor implements TreeVisitor<FelineOverLord> {
    @Override
    public String visit(Empty<FelineOverLord> node) {
        return "";
    }

    @Override
    public String visit(Node<FelineOverLord> node) {
        // post order: links, rechts, Knoten (erst die Kinder, dann der Knoten)
        String links = node.leftChild().accept(this);
        String rechts = node.rightChild().accept(this);
        String daten = node.data().toString();
        return links + rechts + daten;
    }
}
