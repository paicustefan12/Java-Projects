import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item>  {

    private Item[] rq;
    private int size;


    public RandomizedQueue() {
        rq = (Item[]) new Object[1];
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private void resize(int capacity) {
        assert capacity >= size;
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            copy[i] = rq[i];
        }
        rq = copy;
    }

    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException("Item does not exist");
        rq[size++] = item;
        if (size == rq.length) resize(2 * rq.length);
    }

    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        int rand = StdRandom.uniform(size);
        Item item = rq[rand];
        rq[rand] = rq[size - 1];
        rq[size - 1] = null;
        size--;
        if (size > 0 && size == rq.length / 4) resize(rq.length / 2);
        return item;
    }

    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        int rand = StdRandom.uniform(size);
        return rq[rand];
    }

    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }
    // TODO:Iterator
    private class ArrayIterator implements Iterator<Item> {
        private int i = 0;

        public boolean hasNext() {
            return i < size;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            int rand = StdRandom.uniform(size);
            Item item = rq[rand];
            i++;
            return item;
        }
    }

    public static void main(String[] args) {
    RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<>();
    randomizedQueue.enqueue(2);
    System.out.println(randomizedQueue.size());
    randomizedQueue.enqueue(4);
    randomizedQueue.enqueue(5);
        System.out.println(randomizedQueue.dequeue());
    }
}
