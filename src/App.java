import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;




public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.print("Select an option: \n1. Generate random array\n2. Sort and store array\n3. Exit\n");
            int option = scanner.nextInt();
            scanner.nextLine(); 
            switch (option) {
                case 1:
                    System.out.print("Enter the length of the array: ");
                    int arrayLength = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    System.out.print("Enter the file name to store the array: ");
                    String fileName = scanner.nextLine();
                    int[] array = createRandomArray(arrayLength);
                    writeArrayToFile(array, fileName);
                    break;
                case 2:
                    System.out.print("Enter the file name to read the array: ");
                    String fileName2 = scanner.nextLine();
                    int[] readArray = readFileToArray(fileName2);
                    bubbleSort(readArray);
                    System.out.print("Enter the file name to store the sorted array: ");
                    String fileName3 = scanner.nextLine();
                    writeArrayToFile(readArray, fileName3);
                    break;
                case 3:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        } while (true);
    }
public static int[] createRandomArray(int arrayLength) {
    int[] array = new int[arrayLength];
    for (int i = 0; i < array.length; i++) {
        array[i] = (int) (Math.random() * 100);
    }
    return array;
}
public static void writeArrayToFile(int[] array, String fileName) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
        for (int i = 0; i < array.length; i++) {
            writer.write(String.valueOf(array[i]));
            if (i < array.length - 1) {
                writer.write("\n");
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}
public static int[] readFileToArray(String fileName) {
    List<Integer> list = new ArrayList<>();
    try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] numbers = line.split("\n");
            for (String number : numbers) {
                list.add(Integer.parseInt(number));
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
    return list.stream().mapToInt(i -> i).toArray();
}
public static void bubbleSort(int[] array) {
    boolean swapped;
    do {
        swapped = false;
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i + 1]) {
                int temp = array[i];
                array[i] = array[i + 1];
                array[i + 1] = temp;
                swapped = true;
            }
        }
    } while (swapped);
}
}
