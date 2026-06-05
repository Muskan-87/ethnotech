class Post {

    String content;
    Post prev;
    Post next;

    Post(String content) {
        this.content = content;
        this.prev = null;
        this.next = null;
    }
}

public class SocialMediaFeed {

    Post head;
    Post current;

    // Add Normal Post
    void addPost(String content) {

        Post newPost = new Post(content);

        if(head == null) {
            head = newPost;
            current = head;
            return;
        }

        Post temp = head;

        while(temp.next != null) {
            temp = temp.next;
        }

        temp.next = newPost;
        newPost.prev = temp;
    }

    // Insert Sponsored Post at Beginning
    void insertSponsoredPost(String content) {

        Post sponsored = new Post("[SPONSORED] " + content);

        sponsored.next = head;

        if(head != null) {
            head.prev = sponsored;
        }

        head = sponsored;
    }

    // Display Feed
    void displayFeed() {

        Post temp = head;

        System.out.println("\n----- FEED -----");

        while(temp != null) {
            System.out.println(temp.content);
            temp = temp.next;
        }
    }

    // Next Post
    void nextPost() {

        if(current != null && current.next != null) {
            current = current.next;
            System.out.println("Current Post: " + current.content);
        }
        else {
            System.out.println("No Next Post");
        }
    }

    // Previous Post
    void previousPost() {

        if(current != null && current.prev != null) {
            current = current.prev;
            System.out.println("Current Post: " + current.content);
        }
        else {
            System.out.println("No Previous Post");
        }
    }

    // Delete Post
    void deletePost(String content) {

        Post temp = head;

        while(temp != null &&
              !temp.content.equals(content)) {

            temp = temp.next;
        }

        if(temp == null) {
            System.out.println("Post Not Found");
            return;
        }

        if(temp == head) {

            head = head.next;

            if(head != null) {
                head.prev = null;
            }
        }
        else {

            if(temp.prev != null) {
                temp.prev.next = temp.next;
            }

            if(temp.next != null) {
                temp.next.prev = temp.prev;
            }
        }

        System.out.println("Post Deleted");
    }

    public static void main(String[] args) {

        SocialMediaFeed feed = new SocialMediaFeed();

        feed.addPost("Muskan uploaded a photo");
        feed.addPost("Java Linked List Tutorial");
        feed.addPost("New Job Opportunity");

        feed.insertSponsoredPost("Buy Premium Membership");

        feed.displayFeed();

        System.out.println("\nNavigation:");

        feed.nextPost();
        feed.nextPost();
        feed.previousPost();

        System.out.println("\nDeleting a Post:");

        feed.deletePost("Java Linked List Tutorial");

        feed.displayFeed();
    }
}