package проект;

import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.Comparator;

public class LinkedList<T> {

    private class Node {
        T data;
        Node next;

        Node(T data) {
            this.data = data;
        }
    }

    private Node head;

    public void add(T data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }

    public void forEach(Consumer<T> action) {
        Node current = head;
        while (current != null) {
            action.accept(current.data);
            current = current.next;
        }
    }

    public void removeIf(Predicate<T> predicate) {
        while (head != null && predicate.test(head.data)) {
            head = head.next;
        }

        Node current = head;
        while (current != null && current.next != null) {
            if (predicate.test(current.next.data)) {
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }
    }

    public T find(Predicate<T> predicate) {
        Node current = head;
        while (current != null) {
            if (predicate.test(current.data)) {
                return current.data;
            }
            current = current.next;
        }
        return null;
    }

    public void sort(Comparator<T> comparator) {
        if (head == null || head.next == null) return;

        Node sorted = null;
        Node current = head;

        while (current != null) {
            Node next = current.next;

            if (sorted == null || comparator.compare(current.data, sorted.data) < 0) {
                current.next = sorted;
                sorted = current;
            } else {
                Node temp = sorted;
                while (temp.next != null && comparator.compare(current.data, temp.next.data) >= 0) {
                    temp = temp.next;
                }
                current.next = temp.next;
                temp.next = current;
            }

            current = next;
        }

        head = sorted;
    }
}