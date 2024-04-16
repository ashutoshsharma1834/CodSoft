import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Number of Subjects ... ");
        int n = sc.nextInt();
        double arr[] = new double[n];
        for (int i = 0; i < n; i++) {
            System.out.println("Enter Marks of " + (i + 1) + " subject");
            arr[i] = sc.nextDouble();
        }
        double totalMarks = 0, avgMarks = 0;
        for (int i = 0; i < n; i++) {
            totalMarks += arr[i];
        }
        avgMarks = totalMarks / n;
        System.out.println("\nTotal Marks is " + totalMarks);
        System.out.println("\nPercentage is " + avgMarks + "%\n");

        if (avgMarks > 90) {
            System.out.println("Grade : A");
        } else if (avgMarks < 90 && avgMarks > 80) {
            System.out.println("Grade : B");
        } else if (avgMarks < 80 && avgMarks > 70) {
            System.out.println("Grade : C");
        } else if (avgMarks < 70 && avgMarks > 60) {
            System.out.println("Grade : D");
        } else if (avgMarks < 60 && avgMarks > 50) {
            System.out.println("Grade : E");
        } else {
            System.out.println("Grade : F");

        }
        sc.close();
    }
}
