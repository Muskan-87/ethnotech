import java.util.Arrays;
import java.util.Scanner;

public class ArrayRotation {

    // Left Rotation Function
    static int[] leftRotate(int[] arr, int k) {

        int n = arr.length;
        k = k % n;

        int[] result = new int[n];

        for(int i = 0; i < n; i++) {
            result[i] = arr[(i + k) % n];
        }

        return result;
    }

    // Right Rotation Function
    static int[] rightRotate(int[] arr, int k) {

        int n = arr.length;
        k = k % n;

        int[] result = new int[n];

        for(int i = 0; i < n; i++) {
            result[(i + k) % n] = arr[i];
        }

        return result;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter size of array: ");
        int n = sc.nextInt();

        int[] arr = new int[n];

        System.out.println("Enter array elements:");

        for(int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        System.out.print("Enter K value: ");
        int k = sc.nextInt();

        int[] left = leftRotate(arr, k);
        int[] right = rightRotate(arr, k);

        System.out.println("Left Rotation : "
                + Arrays.toString(left));

        System.out.println("Right Rotation : "
                + Arrays.toString(right));

        sc.close();
    }
}