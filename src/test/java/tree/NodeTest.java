package tree;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

/** Testing the {@link Node} class. */
public class NodeTest {

    /**
     * Dummy class for parameterisation of the {@link Tree} class in test.
     *
     * @param name dummy name
     * @param number dummy number (used for comparison)
     */
    private record Dummy(String name, int number) implements Comparable<Dummy> {
        @Override
        public int compareTo(Dummy o) {
            return number - o.number;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    /** Ctor should not allow {@code null} data. */
    @Test
    public void testNodeCtorDataNull() {
        assertThrows(
                NullPointerException.class,
                () -> new Node<Dummy>(new Empty<>(), null, new Empty<>()));
    }

    /** Ctor should not allow {@code null} left child. */
    @Test
    public void testNodeCtorLeftNull() {
        assertThrows(
                NullPointerException.class,
                () -> new Node<>(null, new Dummy("wuppie", 3), new Empty<>()));
    }

    /** Ctor should not allow {@code null} right child. */
    @Test
    public void testNodeCtorRigtNull() {
        assertThrows(
                NullPointerException.class,
                () -> new Node<>(new Empty<>(), new Dummy("wuppie", 3), null));
    }

    /** Ctor should create a single node. */
    @Test
    public void testCtorNode() {
        Dummy c = new Dummy("wuppie", 3);
        Empty<Dummy> e = new Empty<>();

        Tree<Dummy> n = new Node<>(e, c, e);
        // A(,)

        assertEquals(c, n.data());
        assertEquals(e, n.leftChild());
        assertEquals(e, n.rightChild());
    }

    /** The getter for the data object should return the stored reference to this data object. */
    @Test
    public void testGetData() {
        Dummy c1 = new Dummy("wuppie", 1);
        Empty<Dummy> e = new Empty<>();
        Tree<Dummy> n = new Node<>(e, c1, e);
        // A(, )

        assertEquals(c1, n.data());
    }

    /**
     * The getter for the leftChild child should return a reference to the node of the left child.
     */
    @Test
    public void testGetEmptyLeftChild() {
        Dummy c1 = new Dummy("wuppie", 1);
        Empty<Dummy> e = new Empty<>();
        Tree<Dummy> n = new Node<>(e, c1, e);

        assertTrue(n.leftChild().isEmpty());
        assertEquals(e, n.leftChild()); // leftChild should be e
    }

    /**
     * The getter for the leftChild child should return a reference to the node of the left child.
     */
    @Test
    public void testGetLeftChild() {
        Dummy c1 = new Dummy("wuppie", 3);
        Dummy c2 = new Dummy("fluppie", 1);
        Empty<Dummy> e = new Empty<>();
        Tree<Dummy> n = new Node<>(e, c1, e);

        n = n.addData(c2); // add as leftChild child

        Tree<Dummy> child = n.leftChild();
        assertEquals(c2, child.data()); // data should be c2
    }

    /**
     * The getter for the rightChild child should return a reference to the node of the right child.
     */
    @Test
    public void testGetEmptyRightChild() {
        Dummy c1 = new Dummy("wuppie", 1);
        Empty<Dummy> e = new Empty<>();
        Tree<Dummy> n = new Node<>(e, c1, e);

        assertTrue(n.rightChild().isEmpty());
        assertEquals(e, n.rightChild()); // rightChild should be e
    }

    /**
     * The getter for the rightChild child should return a reference to the node of the right child.
     */
    @Test
    public void testGetRightChild() {
        Dummy c1 = new Dummy("wuppie", 1);
        Dummy c2 = new Dummy("fluppie", 3);
        Empty<Dummy> e = new Empty<>();
        Tree<Dummy> n = new Node<>(e, c1, e);

        n = n.addData(c2); // add as rightChild child

        Tree<Dummy> child = n.rightChild();
        assertEquals(c2, child.data()); // data should be c2
    }

    /** A {@link Node} is never empty. */
    @Test
    public void testNodeIsEmpty() {
        Dummy c = new Dummy("wuppie", 3);
        Empty<Dummy> e = new Empty<>();

        Tree<Dummy> n = new Node<>(e, c, e);
        // A(,)

        assertFalse(n.isEmpty());
    }

    /** The size of a single node should be one. */
    @Test
    public void testSizeIsEmpty() {
        Dummy c1 = new Dummy("wuppie", 3);
        Empty<Dummy> e = new Empty<>();
        Tree<Dummy> n = new Node<>(e, c1, e);

        assertEquals(1, n.size());
    }

    /** Adding {@code null} as data should not be allowed. */
    @Test
    public void testAddDataNull() {
        Dummy c1 = new Dummy("wuppie", 3);
        Empty<Dummy> e = new Empty<>();
        Tree<Dummy> n = new Node<>(e, c1, e);

        assertThrows(NullPointerException.class, () -> n.addData(null));
    }

    /** Adding the same data to a single {@link Node} shouldn't do anything. */
    @Test
    public void testAddDataSameObject() {
        Dummy c1 = new Dummy("wuppie", 3);
        Empty<Dummy> e = new Empty<>();
        Tree<Dummy> n = new Node<>(e, c1, e);

        n = n.addData(c1);
        // A(,)

        assertEquals(c1, n.data());
        assertEquals(e, n.leftChild());
        assertEquals(e, n.rightChild());
        assertEquals(1, n.size());
    }

    /** Adding new data to a single {@link Node} should add a new child (here: leftChild child). */
    @Test
    public void testAddDataEmptyLeft() {
        Dummy c1 = new Dummy("wuppie", 3);
        Dummy c2 = new Dummy("fluppie", 2);
        Empty<Dummy> e = new Empty<>();
        Tree<Dummy> n = new Node<>(e, c1, e);

        n = n.addData(c2); // c1 > c2: add c2 as new leftChild child
        // A(B(,), )

        assertEquals(2, n.size());

        assertEquals(c1, n.data());
        assertFalse(n.leftChild().isEmpty());
        assertTrue(n.rightChild().isEmpty());

        assertEquals(c2, n.leftChild().data());
        assertTrue(n.leftChild().leftChild().isEmpty());
        assertTrue(n.leftChild().rightChild().isEmpty());
    }

    /** Adding new data to a single {@link Node} should add a new child (here: rightChild child). */
    @Test
    public void testAddDataEmptyRight() {
        Dummy c1 = new Dummy("wuppie", 3);
        Dummy c2 = new Dummy("fluppie", 4);
        Empty<Dummy> e = new Empty<>();
        Tree<Dummy> n = new Node<>(e, c1, e);

        n = n.addData(c2); // c1 < c2: add c2 as new rightChild child
        // A(, B(,))

        assertEquals(2, n.size());

        assertEquals(c1, n.data());
        assertTrue(n.leftChild().isEmpty());
        assertFalse(n.rightChild().isEmpty());

        assertEquals(c2, n.rightChild().data());
        assertTrue(n.rightChild().leftChild().isEmpty());
        assertTrue(n.rightChild().leftChild().isEmpty());
    }

    /** Adding data existing in the tree should do nothing. */
    @Test
    public void testAddDataDuplicateRecursiveLeft() {
        Dummy c1 = new Dummy("wuppie", 3);
        Dummy c2 = new Dummy("fluppie", 2);
        Dummy c3 = new Dummy("foo", 2);
        Empty<Dummy> e = new Empty<>();
        Tree<Dummy> n = new Node<>(e, c1, e);

        n = n.addData(c2); // c1 > c2: add c2 as new leftChild child
        n = n.addData(c3); // c2 == c3: do not overwrite c2
        // A(C(,), )

        assertEquals(2, n.size());

        assertEquals(c1, n.data());
        assertFalse(n.leftChild().isEmpty());
        assertTrue(n.rightChild().isEmpty());

        assertEquals(c2, n.leftChild().data());
        assertTrue(n.leftChild().leftChild().isEmpty());
        assertTrue(n.leftChild().rightChild().isEmpty());
    }

    /** Adding data existing in the tree should do nothing. */
    @Test
    public void testAddDataDuplicateRecursiveRight() {
        Dummy c1 = new Dummy("wuppie", 3);
        Dummy c2 = new Dummy("fluppie", 4);
        Dummy c3 = new Dummy("foo", 4);
        Empty<Dummy> e = new Empty<>();
        Tree<Dummy> n = new Node<>(e, c1, e);

        n = n.addData(c2); // c1 < c2: add c2 as new rightChild child
        n = n.addData(c3); // c2 == c3: do not overwrite c2
        // A(, C(,))

        assertEquals(2, n.size());

        assertEquals(c1, n.data());
        assertTrue(n.leftChild().isEmpty());
        assertFalse(n.rightChild().isEmpty());

        assertEquals(c2, n.rightChild().data());
        assertTrue(n.rightChild().leftChild().isEmpty());
        assertTrue(n.rightChild().rightChild().isEmpty());
    }

    /** Adding new data to a tree should add a new {@link Node} to the tree (recursive case). */
    @Test
    public void testAddDataEmptyLL() {
        Dummy c1 = new Dummy("wuppie", 3);
        Dummy c2 = new Dummy("fluppie", 2);
        Dummy c3 = new Dummy("foo", 1);
        Empty<Dummy> e = new Empty<>();
        Tree<Dummy> n = new Node<>(e, c1, e);

        n = n.addData(c2); // c1 > c2: add c2 as new leftChild child
        n = n.addData(c3); // c1 > c2 > c3: add c3 as new leftChild-leftChild child
        // A(B(C(,),), )

        assertEquals(3, n.size());

        assertEquals(c1, n.data());
        assertFalse(n.leftChild().isEmpty());
        assertTrue(n.rightChild().isEmpty());

        assertEquals(c2, n.leftChild().data());
        assertFalse(n.leftChild().leftChild().isEmpty());
        assertTrue(n.leftChild().rightChild().isEmpty());

        assertEquals(c3, n.leftChild().leftChild().data());
        assertTrue(n.leftChild().leftChild().leftChild().isEmpty());
        assertTrue(n.leftChild().leftChild().rightChild().isEmpty());
    }

    /** Adding new data to a tree should add a new {@link Node} to the tree (recursive case). */
    @Test
    public void testAddDataEmptyLR() {
        Dummy c1 = new Dummy("wuppie", 3);
        Dummy c2 = new Dummy("fluppie", 1);
        Dummy c3 = new Dummy("foo", 2);
        Empty<Dummy> e = new Empty<>();
        Tree<Dummy> n = new Node<>(e, c1, e);

        n = n.addData(c2); // c1 > c2: add c2 as new leftChild child
        n = n.addData(c3); // c1 > c3 > c2: add c3 as new leftChild-rightChild child
        // A(B(, C(,)), )

        assertEquals(3, n.size());

        assertEquals(c1, n.data());
        assertFalse(n.leftChild().isEmpty());
        assertTrue(n.rightChild().isEmpty());

        assertEquals(c2, n.leftChild().data());
        assertTrue(n.leftChild().leftChild().isEmpty());
        assertFalse(n.leftChild().rightChild().isEmpty());

        assertEquals(c3, n.leftChild().rightChild().data());
        assertTrue(n.leftChild().rightChild().leftChild().isEmpty());
        assertTrue(n.leftChild().rightChild().rightChild().isEmpty());
    }

    /** Adding new data to a tree should add a new {@link Node} to the tree (recursive case). */
    @Test
    public void testAddDataEmptyRL() {
        Dummy c1 = new Dummy("wuppie", 1);
        Dummy c2 = new Dummy("fluppie", 3);
        Dummy c3 = new Dummy("foo", 2);
        Empty<Dummy> e = new Empty<>();
        Tree<Dummy> n = new Node<>(e, c1, e);

        n = n.addData(c2); // c1 < c2: add c2 as new rightChild child
        n = n.addData(c3); // c1 < c3 < c2: add c3 as new rightChild-leftChild child
        // A(, B(C(,), ))

        assertEquals(3, n.size());

        assertEquals(c1, n.data());
        assertTrue(n.leftChild().isEmpty());
        assertFalse(n.rightChild().isEmpty());

        assertEquals(c2, n.rightChild().data());
        assertFalse(n.rightChild().leftChild().isEmpty());
        assertTrue(n.rightChild().rightChild().isEmpty());

        assertEquals(c3, n.rightChild().leftChild().data());
        assertTrue(n.rightChild().leftChild().leftChild().isEmpty());
        assertTrue(n.rightChild().leftChild().rightChild().isEmpty());
    }

    /** Adding new data to a tree should add a new {@link Node} to the tree (recursive case). */
    @Test
    public void testAddDataEmptyRR() {
        Dummy c1 = new Dummy("wuppie", 1);
        Dummy c2 = new Dummy("fluppie", 2);
        Dummy c3 = new Dummy("foo", 3);
        Empty<Dummy> e = new Empty<>();
        Tree<Dummy> n = new Node<>(e, c1, e);

        n = n.addData(c2); // c1 < c2: add c2 as new rightChild child
        n = n.addData(c3); // c1 < c2 < c3: add c3 as new rightChild-rightChild child
        // A(, B(, C(,)))

        assertEquals(3, n.size());

        assertEquals(c1, n.data());
        assertTrue(n.leftChild().isEmpty());
        assertFalse(n.rightChild().isEmpty());

        assertEquals(c2, n.rightChild().data());
        assertTrue(n.rightChild().leftChild().isEmpty());
        assertFalse(n.rightChild().rightChild().isEmpty());

        assertEquals(c3, n.rightChild().rightChild().data());
        assertTrue(n.rightChild().rightChild().leftChild().isEmpty());
        assertTrue(n.rightChild().rightChild().rightChild().isEmpty());
    }

    /** Accepting a {@code null} visitor should not be allowed. */
    @Test
    public void testAcceptNullVisitor() {
        Dummy c1 = new Dummy("wuppie", 1);
        Empty<Dummy> e = new Empty<>();
        Tree<Dummy> n = new Node<>(e, c1, e);

        assertThrows(NullPointerException.class, () -> n.accept(null));
    }

    /** When accepting a visitor we should call {@code visitor.InOrderVisitor.visit(node)}. */
    @Test
    public void testAcceptVisitor() {
        Dummy c1 = new Dummy("wuppie", 1);
        Empty<Dummy> e = new Empty<>();
        Node<Dummy> n = new Node<>(e, c1, e);
        // A(, )

        assertEquals(
                "wuppie",
                n.accept(
                        new TreeVisitor<Dummy>() {
                            @Override
                            public String visit(Empty<Dummy> node) {
                                return "";
                            }

                            @Override
                            public String visit(Node<Dummy> node) {
                                return node.data().toString();
                            }
                        }));
    }

    /** Iterating should not quite be possible. */
    @Test
    public void testIterator() {
        Dummy c1 = new Dummy("wuppie", 1);
        Dummy c2 = new Dummy("fluppie", 3);
        Dummy c3 = new Dummy("foo", 2);
        Empty<Dummy> e = new Empty<>();
        Tree<Dummy> n = new Node<>(e, c1, e);

        n = n.addData(c2); // c1 < c2: add c2 as new rightChild child
        n = n.addData(c3); // c1 < c3 < c2: add c3 as new rightChild-leftChild child
        // A(, B(C(,),))

        Iterator<Dummy> i = n.iterator();

        assertNotNull(i);
        assertTrue(i.hasNext());
    }

    /** Iterating should not quite be possible. */
    @Test
    public void testForEach() {
        Dummy c1 = new Dummy("wuppie", 1);
        Dummy c2 = new Dummy("fluppie", 3);
        Dummy c3 = new Dummy("foo", 2);
        Empty<Dummy> e = new Empty<>();
        Tree<Dummy> n = new Node<>(e, c1, e);

        n = n.addData(c2); // c1 < c2: add c2 as new rightChild child
        n = n.addData(c3); // c1 < c3 < c2: add c3 as new rightChild-leftChild child
        // A(, B(C(,),))

        int count = 0;
        int number = 0;
        String string = "";

        for (Dummy d : n) {
            ++count;
            number += d.number;
            string += d.name;
        }

        assertEquals(3, count);
        assertEquals(6, number);
        assertEquals("wuppiefoofluppie", string);
    }

    /** Iterating should not quite be possible. */
    @Test
    public void testSpliterator() {
        Dummy c1 = new Dummy("wuppie", 1);
        Dummy c2 = new Dummy("fluppie", 3);
        Dummy c3 = new Dummy("foo", 2);
        Empty<Dummy> e = new Empty<>();
        Tree<Dummy> n = new Node<>(e, c1, e);

        n = n.addData(c2); // c1 < c2: add c2 as new rightChild child
        n = n.addData(c3); // c1 < c3 < c2: add c3 as new rightChild-leftChild child
        // A(, B(C(,),))

        Spliterator<Dummy> s = n.spliterator();

        assertNotNull(s);
        assertNotNull(s.trySplit());
        assertFalse(s.tryAdvance(Dummy::toString));
    }

    /** Iterating should not quite be possible. */
    @Test
    public void testStream() {
        Dummy c1 = new Dummy("wuppie", 1);
        Dummy c2 = new Dummy("fluppie", 3);
        Dummy c3 = new Dummy("foo", 2);
        Empty<Dummy> e = new Empty<>();
        Tree<Dummy> n = new Node<>(e, c1, e);

        n = n.addData(c2); // c1 < c2: add c2 as new rightChild child
        n = n.addData(c3); // c1 < c3 < c2: add c3 as new rightChild-leftChild child
        // A(, B(C(,),))

        long count = n.stream().map(Dummy::toString).count();
        int number = n.stream().map(x -> x.number).reduce(0, (x, a) -> x + a);
        String string = n.stream().map(x -> x.name).collect(Collectors.joining());

        assertEquals(3, count);
        assertEquals(6, number);
        assertEquals("wuppiefoofluppie", string);
    }
}
