package ring;

public class Ring<T> {
    private int size = 0;
    private Node current;

    Ring() {}

    public T current() {
        return (current == null) ? null : current.item;
    }

    public int size() {
        return size;
    }

    public void add(T item) {
        if (current == null) current = new Node(item);
        else {
            Node newNode = new Node(item, current, current.next);
            current.next = newNode;
            newNode.next.prev = newNode;
        }
        size++;
    }

    public void remove() {
        if (size <= 0) return;
        if (size == 1) current = null;
        else {
            current.prev.next = current.next;
            current.next.prev = current.prev;
            current = current.prev;
        }
        size--;
    }

    public void next() {
        current = current.next;
    }

    public void prev() {
        current = current.prev;
    }

    class Node {
        T item;
        Node prev;
        Node next;

        Node(T item) {
            this.item = item;
            prev = next = this;
        }

        Node(T item, Node prev, Node next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }
}
