import java.util.*;
public class prodofarrexceptself {
    public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n=sc.nextInt();
    int[] arr = new int[n];
    for(int i=0;i<n;i++){   
        arr[i]=sc.nextInt();
    }
    int res[]=new int[n];
    for(int i=0;i<n;i++){
        int prod=1;
        for(int j=0;j<n;j++){
            if(i!=j){
                prod=prod*arr[j];
            }
        }
        res[i]=prod;
    }
    System.out.println("The product of array except self is:" + Arrays.toString(res));      
   
    }

    
}
