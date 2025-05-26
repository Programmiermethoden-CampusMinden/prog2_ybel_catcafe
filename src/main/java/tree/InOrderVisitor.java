package tree;

import catcafe.FelineOverLord;

public class InOrderVisitor implements TreeVisitor<FelineOverLord>
{
    @Override
    public String visit(Empty<FelineOverLord> node)
    {
        return "";
    }

    @Override
    public String visit(Node<FelineOverLord> node)
    {
        return helpVisit(node);
    }

    public String helpVisit(Tree<FelineOverLord> node) {
        if (node.isEmpty()) {
            return "";
        }
        // linkes Teilbaum
        String left = helpVisit(node.leftChild());
        // aktuelles Daten
        String current = node.data().toString();
        // rechtes Teilbaum
        String right = helpVisit(node.rightChild());

        return left + (left.isEmpty() ? "" : " ") + current + (right.isEmpty() ? "" : " ") + right;
    }


}
