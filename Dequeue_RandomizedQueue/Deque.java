import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node first;
    private Node last;
    private int sz;

    private class Node {
        private Item item;
        private Node next;
        private Node prev;
    }

    public Deque() {
        this.first = null;
        this.last = null;
        sz = 0;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return sz;
    }

    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("item is null");
        }
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        first.prev = null;
        if (size() == 0)
            last = first;
        else oldfirst.prev = first;
        sz++;
    }

    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("item is null");
        }
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        last.prev = oldlast;
        if (size() == 0)
            first = last;
        else oldlast.next = last;
        sz++;
    }

    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("deque is empty");
        }
        Item item = first.item;
        if (size() > 1) {
            first = first.next;
            first.prev = null;
        }
        else {
            first = null;
            last = null;
        }
        sz--;
        return item;
    }

    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("deque is empty");
        }
        Item item = last.item;
        if (size() > 1) {
            last = last.prev;
            last.next = null;
        }
        else {
            first = null;
            last = null;
        }
        sz--;
        return item;
    }


    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException("do not support remove in the iterator class");
        }

        public Item next() {
            if (current == null)
                throw new NoSuchElementException("there isn't a next element");
            else {
                Item item = current.item;
                current = current.next;
                return item;
            }
        }
    }

    public static void main(String[] args) {
        Deque<Integer> d = new Deque<>();
        d.addFirst(3);
        d.addFirst(4);
        d.addLast(5);
        System.out.println(d.size());
        System.out.println(d.removeFirst());
        System.out.println(d.removeFirst());
        System.out.println(d.removeFirst());
        System.out.println(d.size());
    }
}
