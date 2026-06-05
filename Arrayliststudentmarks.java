import java.util.ArrayList;

public class Arrayliststudentmarks {
    public static void main(String[] args) {

        ArrayList<Integer> marks = new ArrayList<>();


        marks.add(85);
        marks.add(90);
        marks.add(78);
        marks.add(67);
        marks.add(88);

       
        System.out.println("Student Marks:");
        for (int mark : marks) {
            System.out.print(mark + " ");
        }
    }
}
