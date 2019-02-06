package your_code;

public class MyLinkedList {

    private Node head;
    private Node tail;
    private int size;

    private class Node {
        Chicken val;
        Node prev;
        Node next;

        private Node(Chicken d, Node prev, Node next) {
            this.val = d;
            this.prev = prev;
            this.next = next;
        }

        private Node(Chicken d) {
            this.val = d;
            prev = null;
            next = null;
        }
    }

    public MyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(Chicken c) {
        addLast(c);
    }

    public Chicken pop() {
        return removeLast();
    }

    public void addLast(Chicken c) {
        if (size == 0){
            head = new Node(c);
            tail = head;

        }
        else {
            tail.next = new Node(c, tail, null);
            tail = tail.next;

        }
        size++;
    }

    public void addFirst(Chicken c) {
        if (size == 0){
            head = new Node(c);
            tail = head;

        }
        else {
            head.prev = new Node(c, null, head);
            head = head.prev;

        }
        size++;
    }

    public Chicken get(int index) {
        Node target = head;
        if (index >= 0 && index<=size-1){
            for (int i = 0; i < index; i++){
                target = target.next;
            }
            return target.val;
        }
        else{
            throw new IndexOutOfBoundsException();
        }
    }

    public Chicken remove(int index) {
        if (index == 0){
            return removeFirst();
        }
        else if (index == size - 1){
            return removeLast();
        }
        else if (index >= 1 && index<=size-1){
            Node target = head;
            for (int i = 0; i < index; i++){
                target = target.next;
            }
            target.prev.next = target.next;
            target.next.prev = target.prev;
            size --;
            return target.val;
        }
        else {return null;}
    }

    public Chicken removeFirst() {
        if (size == 0){
            return null;
        }
        else if (size == 1){
            Chicken temp_chicken = head.val;
            head = null;
            tail = null;
            size --;
            return temp_chicken;
        }
        else{
            Chicken temp_chicken = head.val;
            head = head.next;
            head.prev = null;
            size--;
            return temp_chicken;
        }
    }

    public Chicken removeLast() {
        if (size == 0){
            return null;
        }
        else if (size == 1){
            Chicken temp_chicken = head.val;
            head = null;
            tail = null;
            size --;
            return temp_chicken;
        }
        else{
            Chicken temp_chicken = tail.val;
            tail = tail.prev;
            tail.next = null;
            size--;
            return temp_chicken;
        }
    }
}