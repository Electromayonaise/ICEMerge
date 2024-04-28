import BucketICE.*;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import com.zeroc.Ice.*;
import java.util.concurrent.CompletableFuture;
import java.util.random.*;


public class Client {

    Scanner scanner;

    public static void main(String[] args) throws java.lang.Exception {
        try(Communicator communicator = Util.initialize(args, "client.config")) {
            ObjectPrx base = communicator.propertyToProxy("primaryServer.proxy");
            DistributedSortingPrx sorter = DistributedSortingPrx.checkedCast(base);
            if(sorter == null) {
                throw new Error("Invalid proxy");
            }
            System.out.println("Connected to the server");
            Scanner scanner = new Scanner(System.in);
            boolean exit = false;
            while(!exit){
                System.out.println("1. Sort a ran array of a given size");
                System.out.println("2. Exit");
                int choice = scanner.nextInt();
                double a, b;
                switch(choice) {
                    case 1:
                        System.out.println("Enter the size");
                        int size=scanner.nextInt();
                       //  System.out.println(Arrays.toString( (int[])sorter.getArr() ));
                       int[] arr = new Random().ints(size, 1, 100).toArray();
                       long startTime = System.currentTimeMillis();
                        CompletableFuture<int[]> answer2= sorter.sortAsync(arr);
                        int[] result=(int[])answer2.get();
                        long endTime = System.currentTimeMillis();
                        long duration = endTime - startTime;
                        System.out.println("BEFORE");
                        System.out.println(Arrays.toString(arr));
                        System.out.println("AFTER");
                        System.out.println(Arrays.toString(result) );
                        System.out.println("DURATION IN ML"+ duration);
                        
                        break;
                    case 2:
                        exit = true;
                        break;

                    default:
                        System.out.println("Invalid choice");

                }
            }
            scanner.close();
        }
    }
} 