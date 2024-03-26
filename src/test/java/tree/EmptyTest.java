package tree;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

/** Testing the {@link Empty} class. */
public class EmptyTest {

    /** Dummy class for parameterisation of the {@link Tree} class in test. */
    private record Dummy() implements Comparable<Dummy> {
        @Override
        public int compareTo(Dummy o) {
            return 0;
        }
    }

    /** Ctor should create a single empty node. */
    @Test
    public void testCtorNode() {
        Tree<Dummy> e = new Empty<>();

        assertThrows(UnsupportedOperationException.class, () -> e.data());
        assertThrows(UnsupportedOperationException.class, () -> e.leftChild());
        assertThrows(UnsupportedOperationException.class, () -> e.rightChild());
    }

    /** The getter for the data object should throw an UnsupportedOperationException. */
    @Test
    public void testGetData() {
        Tree<Dummy> e = new Empty<>();

        assertThrows(UnsupportedOperationException.class, () -> e.data());
    }

    /** The getter for the leftChild child should throw an UnsupportedOperationException. */
    @Test
    public void testGetLeftChild() {
        Tree<Dummy> e = new Empty<>();

        assertThrows(UnsupportedOperationException.class, () -> e.leftChild());
    }

    /** The getter for the leftChild child should throw an UnsupportedOperationException. */
    @Test
    public void testGetRightChild() {
        Tree<Dummy> e = new Empty<>();

        assertThrows(UnsupportedOperationException.class, () -> e.rightChild());
    }

    /** This node is always empty. */
    @Test
    public void testNodeIsEmpty() {
        Tree<Dummy> e = new Empty<>();

        assertTrue(e.isEmpty());
    }

    /** The size of an empty node should be zero. */
    @Test
    public void testSizeIsEmpty() {
        Tree<Dummy> e = new Empty<>();

        assertEquals(0, e.size());
    }

    /** Adding {@code null} as data should not be allowed. */
    @Test
    public void testAddDataNull() {
        Tree<Dummy> e = new Empty<>();

        assertThrows(NullPointerException.class, () -> e.addData(null));
    }

    /** Adding data to a single {@link Empty} should build a new {@link Node}. */
    @Test
    public void testAddData() {
        Dummy c1 = new Dummy();
        Tree<Dummy> e = new Empty<>();

        e = e.addData(c1);
        // A(,)

        assertEquals(c1, e.data());
        assertTrue(e.leftChild().isEmpty());
        assertTrue(e.rightChild().isEmpty());
    }

    /** Accepting a {@code null} visitor should not be allowed. */
    @Test
    public void testAcceptNullVisitor() {
        Empty<Dummy> e = new Empty<>();

        assertThrows(NullPointerException.class, () -> e.accept(null));
    }

    /** When accepting a visitor we should call {@code visitor.InOrderVisitor.visit(node)}. */
    @Test
    public void testAcceptVisitor() {
        Empty<Dummy> e = new Empty<>();

        assertEquals(
                "",
                e.accept(
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
        Tree<Dummy> e = new Empty<>();

        Iterator<Dummy> i = e.iterator();

        assertNotNull(i);
        assertFalse(i.hasNext());
    }

    /** Iterating should not quite be possible. */
    @Test
    public void testForEach() {
        Tree<Dummy> e = new Empty<>();
        int count = 0;

        for (Dummy d : e) ++count;

        assertEquals(0, count);
    }

    /** Iterating should not quite be possible. */
    @Test
    public void testSpliterator() {
        Tree<Dummy> e = new Empty<>();

        Spliterator<Dummy> s = e.spliterator();

        assertNotNull(s);
        assertNull(s.trySplit());
        assertFalse(s.tryAdvance(Dummy::toString));
    }

    /** Iterating should not quite be possible. */
    @Test
    public void testStream() {
        Tree<Dummy> e = new Empty<>();
        Stream<Dummy> s = e.stream();

        long c = s.map(Dummy::toString).count();

        assertNotNull(s);
        assertEquals(0, c);
    }
}
