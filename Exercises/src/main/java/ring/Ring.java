package ring;

import java.util.Iterator;

public class Ring<T> implements Iterable<T> {
    private int size = 0;
    private Node current;

    Ring() {}

    public T get() {
        return isEmpty() ? null : current.item;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void add(T item) {
        Node newNode = new Node(item);
        if (current != null) {
            Node nextNode = current.next;

            newNode.prev = current;
            newNode.next = nextNode;

            current.next = newNode;
            nextNode.prev = newNode;
        }

        current = newNode;
        size++;
    }

    public void remove() {
        if (size <= 0) throw new EmptyRingException("Operation not valid on an empty Ring");
        if (size == 1) current = null;
        else {
            Node previousNode = current.prev;
            Node nextNode = current.next;

            previousNode.next = nextNode;
            nextNode.prev = previousNode;
            current = previousNode;
        }
        size--;
    }

    public void next() {
        if (current == null)
            throw new EmptyRingException("Operation not valid on an empty Ring");
        current = current.next;
    }

    public void prev() {
        if (current == null)
            throw new EmptyRingException("Operation not valid on an empty Ring");
        current = current.prev;
    }

    private class Node {

        T item;
        Node prev;
        Node next;
        Node(T item) {
            this.item = item;
            prev = next = this;
        }

    }

    @Override
    public Iterator<T> iterator() {
        return new RingIterator();
    }

    class RingIterator implements Iterator<T> {
        int pos = 0;

        @Override
        public boolean hasNext() {
            return pos < size;
        }

        @Override
        public T next() {
            T item = get();
            Ring.this.next();
            pos++;

            return item;
        }
    }
}
