import BucketICE.*;
import com.zeroc.Ice.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.io.IOException;
import java.util.Arrays;
import java.io.IOException;

public class Client {

    public static void main(String[] args) {
        try (Communicator communicator = Util.initialize(args, "client.config")) {
            ObjectPrx base = communicator.propertyToProxy("primaryServer.proxy");
            DistributedSortingPrx sorter = DistributedSortingPrx.checkedCast(base);
            if (sorter == null) {
                throw new Error("Invalid proxy");
            }
            System.out.println("Connected to the server");
            Scanner scanner = new Scanner(System.in);
            boolean exit = false;
            while (!exit) {
                try {
                    System.out.println("Select an option:");
                    System.out.println("1. Enter the path of the file to be sorted");
                    System.out.println("2. Sort sample files");
                    int option = Integer.parseInt(scanner.nextLine());
                    int[] numbers = null;
                    switch (option) {
                        case 1:
                            System.out.println("Enter the path of the file to be sorted:");
                            String path = scanner.nextLine();
                            byte[] content = Files.readAllBytes(Paths.get(path));
                            numbers = convertBytesToIntegers(content);
                            break;
                        case 2:
                            System.out.println("Select a sample file:");
                            System.out.println("1. numbers1.txt");
                            System.out.println("2. numbers2.txt");
                            System.out.println("3. numbers3.txt");
                            System.out.println("4. numbers4.txt");
                            System.out.println("5. numbers5.txt");
                            int sampleFile = Integer.parseInt(scanner.nextLine());
                            switch (sampleFile) {
                                case 1:
                                    numbers = convertBytesToIntegers(Files.readAllBytes(Paths.get("SampleFiles/numbers1.txt")));
                                    break;
                                case 2:
                                    numbers = convertBytesToIntegers(Files.readAllBytes(Paths.get("SampleFiles/numbers2.txt")));
                                    break;
                                case 3:
                                    numbers = convertBytesToIntegers(Files.readAllBytes(Paths.get("SampleFiles/numbers3.txt")));
                                    break;
                                case 4:
                                    numbers = convertBytesToIntegers(Files.readAllBytes(Paths.get("SampleFiles/numbers4.txt")));
                                    break;
                                case 5:
                                    numbers = convertBytesToIntegers(Files.readAllBytes(Paths.get("SampleFiles/numbers5.txt")));
                                    break;
                                default:
                                    System.out.println("Invalid option");
                                    break;
                            }
                    
                            break;
                        default:
                            System.out.println("Invalid option");
                            break;
                    }
                    long startTime = System.currentTimeMillis();
                    CompletableFuture<int[]> sortedContentFuture = sorter.sortAsync(numbers);
                    int[] sortedContent = sortedContentFuture.get();
                    long endTime = System.currentTimeMillis();
                    long duration = endTime - startTime;
                    System.out.println("Sorted content:");
                    System.out.println(Arrays.toString(sortedContent));
                    System.out.println("Duration in milliseconds: " + duration);

                    System.out.println("Do you want to enter another file? (Y/N)");
                    String choice = scanner.nextLine();
                    if (!choice.equalsIgnoreCase("Y")) {
                        exit = true;
                    }
                } catch (IOException e) {
                    System.err.println("Error reading file: " + e.getMessage());
                } catch (java.lang.Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (java.lang.Exception e) {
            e.printStackTrace();
        }
    }

    private static int[] convertBytesToIntegers(byte[] bytes) {
        String[] numbersAsString = new String(bytes).split("\\s+");
        int[] numbers = new int[numbersAsString.length];
        for (int i = 0; i < numbersAsString.length; i++) {
            numbers[i] = Integer.parseInt(numbersAsString[i]);
        }
        return numbers;
    }
}
