package org.example.task5;

import java.util.Objects;

public class five {
    public static void main(String[] args) {
        MyDoublyLinkedList doublyLinkedList = new MyDoublyLinkedList();

        doublyLinkedList.addToStart("two");
        doublyLinkedList.addToStart("one");
        doublyLinkedList.addToEnd("three");
        doublyLinkedList.addToEnd("four");

        System.out.println(doublyLinkedList.toString());

        doublyLinkedList.removeEnd();
        doublyLinkedList.removeStart();

        System.out.println(doublyLinkedList.toString());

    }


}

class MyDoublyLinkedList<T> {
    private Node first;   // Указатель на первый узел в списке
    private Node last;    // Указатель на последний узел в списке

    // Класс узла
    private static class Node<T> {
        private T data;      // Данные элемента списка
        private Node prev;   // Ссылка на предыдущий узел
        private Node next;   // Ссылка на следующий узел

        public Node(T data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }

    /**
     * Добавляет новый узел в конец списка
     *
     * @param data элемент списка
     */
    public void addToEnd(T data) {
        Node node = new Node(data);

        if (first == null) {
            first = node;
            last = node;
        } else {
            last.next = node;
            node.prev = last;
            last = node;
        }
    }

    /**
     * Добавляет новый узел в начало списка
     *
     * @param data элемент списка
     */
    public void addToStart(T data) {
        Node node = new Node(data);

        if (first == null) {
            first = node;
            last = node;
        } else {
            first.prev = node;
            node.next = first;
            first = node;
        }
    }

    /**
     * Удаляет первый узел из списка
     */
    public void removeStart() {
        if (first != null) {
            if (first.next == null) {
                first = null;
                last = null;
            } else {
                first = first.next;
                first.prev = null;
            }
        }
    }

    /**
     * Удаляет последний узел из списка
     */
    public void removeEnd() {
        if (last != null) {
            if (last.prev == null) {
                last = null;
                first = null;
            } else {
                last = last.prev;
                last.next = null;
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node node = first;
        sb.append(node.toString());
        sb.append(" ");

        while (node.next != null) {
            node = node.next;
            sb.append(node.toString());
            sb.append(" ");
        }

        return sb.toString();
    }
}
