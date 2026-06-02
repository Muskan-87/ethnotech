public class JaggedArrayStudentMarks {
    public static void main(String[] args) {

        // Jagged Array
        int[][] marks = {
            {85, 90, 78},              
            {67, 88, 92, 75, 80},      
            {95, 89, 91, 87}           
        };

  
        for (int i = 0; i < marks.length; i++) {

            System.out.println("Student " + (i + 1) + " Marks:");

            for (int j = 0; j < marks[i].length; j++) {
                System.out.print(marks[i][j] + " ");
            }

            System.out.println("\n");
        }
    }
}