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
    System.out.print("Enter the length of the array: ");
    int arrayLength = scanner.nextInt();
    scanner.nextLine();
    System.out.print("Enter the file name to store the array: ");
    String fileName = scanner.nextLine();
    scanner.close();
    int[] array = createRandomArray(arrayLength);
    writeArrayToFile(array, fileName);
    int[] readArray = readFileToArray(fileName);
    long startTime = System.nanoTime();
    bubbleSort(readArray);
    long endTime = System.nanoTime();
    long duration = endTime - startTime;
    System.out.println("Time taken to sort the array: " + duration + " nanoseconds");
    System.out.println("Sorted array: ");
    for (int i = 0; i < readArray.length; i++) {
        System.out.print(readArray[i] + " ");
    }
    
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
                writer.write(",");
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
            String[] numbers = line.split(",");
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
