class Node {

    String page;
    Node next;

    Node(String page) {
        this.page = page;
        this.next = null;
    }
}

public class BrowserHistory {

    Node head;

    // Visit New Page
    void visitPage(String page) {

        Node newNode = new Node(page);

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

    // Display History
    void displayHistory() {

        if(head == null) {
            System.out.println("No Browsing History");
            return;
        }

        Node temp = head;

        System.out.println("\nBrowsing History:");

        while(temp != null) {
            System.out.println(temp.page);
            temp = temp.next;
        }
    }

    // Search Page
    void searchPage(String pageName) {

        Node temp = head;

        while(temp != null) {

            if(temp.page.equalsIgnoreCase(pageName)) {
                System.out.println(pageName + " Found in History");
                return;
            }

            temp = temp.next;
        }

        System.out.println(pageName + " Not Found");
    }

    // Delete Page
    void deletePage(String pageName) {

        if(head == null) {
            return;
        }

        if(head.page.equalsIgnoreCase(pageName)) {
            head = head.next;
            System.out.println(pageName + " Deleted");
            return;
        }

        Node temp = head;

        while(temp.next != null &&
              !temp.next.page.equalsIgnoreCase(pageName)) {

            temp = temp.next;
        }

        if(temp.next != null) {
            temp.next = temp.next.next;
            System.out.println(pageName + " Deleted");
        }
        else {
            System.out.println(pageName + " Not Found");
        }
    }

    // Count Pages
    void countPages() {

        int count = 0;

        Node temp = head;

        while(temp != null) {
            count++;
            temp = temp.next;
        }

        System.out.println("Total Visited Pages: " + count);
    }

    public static void main(String[] args) {

        BrowserHistory history = new BrowserHistory();

        history.visitPage("google.com");
        history.visitPage("youtube.com");
        history.visitPage("github.com");
        history.visitPage("chatgpt.com");

        history.displayHistory();

        history.searchPage("github.com");

        history.countPages();

        history.deletePage("youtube.com");

        System.out.println("\nAfter Deletion:");

        history.displayHistory();

        history.countPages();
    }
}