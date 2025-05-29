package tree;

import catcafe.FelineOverLord;

public class InOrderVisitor implements TreeVisitor<FelineOverLord> {
    @Override
    public String visit(Empty<FelineOverLord> node) {
        return "";
    }

    @Override
    public String visit(Node<FelineOverLord> node) {
        // in order: links, Knoten, rechts
        String links = node.leftChild().accept(this);
        String daten = node.data().toString();
        String rechts = node.rightChild().accept(this);
        return links + daten + rechts;
    }
}
