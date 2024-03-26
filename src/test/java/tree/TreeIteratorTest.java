package tree;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.Test;

/** Testing the {@link TreeIterator} class. */
public class TreeIteratorTest {

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
    public void testCtorRootNull() {
        assertThrows(NullPointerException.class, () -> new TreeIterator(null));
    }

    /** Iterating over empty tree. */
    @Test
    public void testIteratorEmptyTree() {
        Tree<Dummy> e = new Empty<>();

        Iterator<Dummy> i = new TreeIterator<>(e);

        assertFalse(i.hasNext());
        assertThrows(NoSuchElementException.class, () -> i.next());
    }

    /** Iterating over single node. */
    @Test
    public void testIteratorSingleNode() {
        Dummy c = new Dummy("wuppie", 3);
        Empty<Dummy> e = new Empty<>();
        Tree<Dummy> n = new Node<>(e, c, e);
        // A(,)

        Iterator<Dummy> i = new TreeIterator<>(n);

        assertTrue(i.hasNext());
        assertEquals(c, i.next());

        assertFalse(i.hasNext());
        assertThrows(NoSuchElementException.class, () -> i.next());
    }

    /** Iterating over simple tree. */
    @Test
    public void testIteratorLL() {
        Dummy c1 = new Dummy("wuppie", 3);
        Dummy c2 = new Dummy("fluppie", 2);
        Dummy c3 = new Dummy("foo", 1);
        Empty<Dummy> e = new Empty<>();
        Tree<Dummy> n = new Node<>(e, c1, e);

        n = n.addData(c2); // c1 > c2: add c2 as new leftChild child
        n = n.addData(c3); // c1 > c2 > c3: add c3 as new leftChild-leftChild child
        // A(B(C(,),), )

        Iterator<Dummy> i = new TreeIterator<>(n);

        assertTrue(i.hasNext());
        assertEquals(c3, i.next());

        assertTrue(i.hasNext());
        assertEquals(c2, i.next());

        assertTrue(i.hasNext());
        assertEquals(c1, i.next());

        assertFalse(i.hasNext());
        assertThrows(NoSuchElementException.class, () -> i.next());
    }

    /** Iterating over simple tree. */
    @Test
    public void testIteratorLR() {
        Dummy c1 = new Dummy("wuppie", 3);
        Dummy c2 = new Dummy("fluppie", 1);
        Dummy c3 = new Dummy("foo", 2);
        Empty<Dummy> e = new Empty<>();
        Tree<Dummy> n = new Node<>(e, c1, e);

        n = n.addData(c2); // c1 > c2: add c2 as new leftChild child
        n = n.addData(c3); // c1 > c3 > c2: add c3 as new leftChild-rightChild child
        // A(B(, C(,)), )

        Iterator<Dummy> i = new TreeIterator<>(n);

        assertTrue(i.hasNext());
        assertEquals(c2, i.next());

        assertTrue(i.hasNext());
        assertEquals(c3, i.next());

        assertTrue(i.hasNext());
        assertEquals(c1, i.next());

        assertFalse(i.hasNext());
        assertThrows(NoSuchElementException.class, () -> i.next());
    }

    /** Iterating over simple tree. */
    @Test
    public void testIteratorRL() {
        Dummy c1 = new Dummy("wuppie", 1);
        Dummy c2 = new Dummy("fluppie", 3);
        Dummy c3 = new Dummy("foo", 2);
        Empty<Dummy> e = new Empty<>();
        Tree<Dummy> n = new Node<>(e, c1, e);

        n = n.addData(c2); // c1 < c2: add c2 as new rightChild child
        n = n.addData(c3); // c1 < c3 < c2: add c3 as new rightChild-leftChild child
        // A(, B(C(,), ))

        Iterator<Dummy> i = new TreeIterator<>(n);

        assertTrue(i.hasNext());
        assertEquals(c1, i.next());

        assertTrue(i.hasNext());
        assertEquals(c3, i.next());

        assertTrue(i.hasNext());
        assertEquals(c2, i.next());

        assertFalse(i.hasNext());
        assertThrows(NoSuchElementException.class, () -> i.next());
    }

    /** Iterating over simple tree. */
    @Test
    public void testIteratorRR() {
        Dummy c1 = new Dummy("wuppie", 1);
        Dummy c2 = new Dummy("fluppie", 2);
        Dummy c3 = new Dummy("foo", 3);
        Empty<Dummy> e = new Empty<>();
        Tree<Dummy> n = new Node<>(e, c1, e);

        n = n.addData(c2); // c1 < c2: add c2 as new rightChild child
        n = n.addData(c3); // c1 < c2 < c3: add c3 as new rightChild-rightChild child
        // A(, B(, C(,)))

        Iterator<Dummy> i = new TreeIterator<>(n);

        assertTrue(i.hasNext());
        assertEquals(c1, i.next());

        assertTrue(i.hasNext());
        assertEquals(c2, i.next());

        assertTrue(i.hasNext());
        assertEquals(c3, i.next());

        assertFalse(i.hasNext());
        assertThrows(NoSuchElementException.class, () -> i.next());
    }
}
