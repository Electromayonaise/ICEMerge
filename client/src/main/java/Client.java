import ICEBucket.*;
import com.zeroc.Ice.*;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class Client {

    public static void main(String[] args) {
        try (Communicator communicator = Util.initialize(args, "client.config")) {
            ObjectPrx base = communicator.propertyToProxy("primaryServer.proxy");
            PrimaryServerPrx primaryServer = PrimaryServerPrx.checkedCast(base);
            if (primaryServer == null) {
                throw new Error("Invalid proxy");
            }
            Scanner scanner = new Scanner(System.in);

            // Ask user for file path
            System.out.println("Enter the path of the file to be sorted:");
            String filePath = scanner.nextLine();

            // Read file data
            List<String> lines = Files.readAllLines(Paths.get(filePath));

            // Check if the file contains integers or strings
            boolean isInteger = lines.stream().allMatch(Client::isInteger);

            // Convert lines to appropriate type
            List<?> data = isInteger ? lines.stream().map(Integer::parseInt).collect(Collectors.toList()): lines;

            Comparator comparator;

            if (isInteger){
                comparator = new IntegerComparator();
            } else {
                comparator = new StringComparator();
            }


            // Send data to server for sorting
            primaryServer.sortFileAsync(data, comparator);

        } catch (Ice.LocalException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Helper method to check if a string represents an integer
    private static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Helper class to compare integers
    private static class IntegerComparator implements Comparator {
        @Override
        public int compare(Object a, Object b, Current current) {
            return (int) a - (int) b;
        }
    }

    // Helper class to compare strings
    private static class StringComparator implements Comparator {
        @Override
        public int compare(Object a, Object b, Current current) {
            return ((String) a).compareTo((String) b);
        }
    }
}
