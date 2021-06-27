package bearmaps.proj2ab;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {

    private ArrayList<PriorityNode> itemPQ;
    private HashMap<T, Integer> itemMapIndex;

    public ArrayHeapMinPQ() {
        itemPQ =  new ArrayList<>();
        itemMapIndex = new HashMap<>();
    }

    @Override
    public void add(T item, double priority) {
        if (contains(item)) {
            throw new IllegalArgumentException();
        }
        itemPQ.add(new PriorityNode(item, priority));
        itemMapIndex.put(item, size() - 1);
        climb(size() - 1);
    }

    @Override
    public boolean contains(T item) {
        if (isEmpty()) {
            return false;
        }
        return itemMapIndex.containsKey(item);
    }

    @Override
    public T getSmallest() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return itemPQ.get(0).getItem();
    }

    @Override
    public T removeSmallest() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        T toRemove = itemPQ.get(0).getItem();
        swap(0, size() - 1);
        itemPQ.remove(size() - 1);
        itemMapIndex.remove(toRemove);
        sink(0);
        return toRemove;
    }

    @Override
    public int size() {
        return itemPQ.size();
    }

    @Override
    public void changePriority(T item, double priority) {
        if (isEmpty() || !contains(item)) {
            throw new NoSuchElementException();
        }
        int index = itemMapIndex.get(item);
        double oldPriority = itemPQ.get(index).getPriority();
        itemPQ.get(index).setPriority(priority);
        if (oldPriority < priority) {
            sink(index);
        } else {
            climb(index);
        }
    }

    private class PriorityNode {

        private T item;
        private double priority;

        PriorityNode(T item, double priority) {
            this.item = item;
            this.priority = priority;
        }

        T getItem() {
            return item;
        }

        double getPriority() {
            return priority;
        }

        void setPriority(double priority) {
            this.priority = priority;
        }
    }

    private boolean isEmpty() {
        return size() == 0;
    }


    private int parent(int i) {
        if (i == 0) {
            return 0;
        } else {
            return (i - 1) / 2;
        }
    }


    private int leftChild(int i) {
        return 2 * i + 1;
    }

    private int rightChild(int i) {
        return 2 * i + 2;
    }

    private void climb(int i) {
        if (i > 0 && smaller(i, parent(i))) {
            swap(i, parent(i));
            climb(parent(i));
        }
    }

    private void sink(int i) {
        int smallest = i;
        if (leftChild(i) <= size() - 1 && smaller(leftChild(i), i)) {
            smallest = leftChild(i);
        }
        if (rightChild(i) <= size() - 1 && smaller(rightChild(i), smallest)) {
            smallest = rightChild(i);
        }
        if (smallest != i) {
            swap(i, smallest);
            sink(smallest);
        }
    }

    private void swap(int i, int j) {
        PriorityNode temp = itemPQ.get(i);
        itemPQ.set(i, itemPQ.get(j));
        itemPQ.set(j, temp);
        itemMapIndex.put(itemPQ.get(i).getItem(), i);
        itemMapIndex.put(itemPQ.get(j).getItem(), j);
    }

    private boolean smaller(int i, int j) {
        return itemPQ.get(i).getPriority() < itemPQ.get(j).getPriority();
    }

}
