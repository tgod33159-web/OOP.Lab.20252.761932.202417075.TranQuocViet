import java.util.*;
public class ArrayProgram {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter size: ");
        int n = sc.nextInt();

        int[] arr = new int[n];
        int sum = 0;

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
            sum += arr[i];
        }

        Arrays.sort(arr);

        System.out.println("Sorted array:");
        for (int x : arr)
            System.out.print(x + " ");

        double avg = (double) sum / n;
        System.out.println("\nSum = " + sum);
        System.out.println("Average = " + avg);
    }
}