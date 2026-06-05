class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class LinkedListDemo {

    Node head;

    // Insert at Beginning
    void insertAtBeginning(int data) {
        Node newNode = new Node(data);

        newNode.next = head;
        head = newNode;
    }

    // Insert at End
    void insertAtEnd(int data) {
        Node newNode = new Node(data);

        if(head == null) {
            head = newNode;
            return;
        }

        Node temp = head;

        while(temp.next != null) {
            temp = temp.next;
        }

        temp.next = newNode;
    }

    // Delete Node
    void delete(int key) {

        if(head == null) {
            return;
        }

        if(head.data == key) {
            head = head.next;
            return;
        }

        Node temp = head;

        while(temp.next != null && temp.next.data != key) {
            temp = temp.next;
        }

        if(temp.next != null) {
            temp.next = temp.next.next;
        }
    }

    // Search Element
    void search(int key) {

        Node temp = head;

        while(temp != null) {

            if(temp.data == key) {
                System.out.println(key + " Found");
                return;
            }

            temp = temp.next;
        }

        System.out.println(key + " Not Found");
    }

    // Count Nodes
    void countNodes() {

        int count = 0;

        Node temp = head;

        while(temp != null) {
            count++;
            temp = temp.next;
        }

        System.out.println("Total Nodes: " + count);
    }

    // Display List
    void display() {

        Node temp = head;

        while(temp != null) {
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        }

        System.out.println("null");
    }

    public static void main(String[] args) {

        LinkedListDemo list = new LinkedListDemo();

        list.insertAtBeginning(20);
        list.insertAtBeginning(10);

        list.insertAtEnd(30);
        list.insertAtEnd(40);
        list.insertAtEnd(50);

        System.out.println("Linked List:");
        list.display();

        list.search(30);

        list.countNodes();

        list.delete(30);

        System.out.println("After Deletion:");
        list.display();

        list.countNodes();
    }
}