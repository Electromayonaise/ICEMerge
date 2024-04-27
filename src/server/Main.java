package server;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner reader=new Scanner(System.in);
        while (true){
            System.out.println("TYPE THE ARRAY SIZE"); 
            int size=reader.nextInt();
            // Crear un HashMap
            SortingServant sortingServant=new SortingServant();
            int[] arr = new Random().ints(size, 1, 100).toArray();
            long startTime = System.currentTimeMillis();
        
            sortingServant.mergeSort(arr);
            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;
            System.out.println("merge sort test");
            System.out.println(Arrays.toString(arr));
            System.out.println("DURATION IN ML "+ duration);

        }
    
        
        /* 

        System.out.println("MERGE TEST");
        int[] arr1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] arr2 = {11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
        int[] arr3 = {21, 22, 23, 24, 25, 26, 27, 28, 29, 30};
        int[] arr4 = {31, 32, 33, 34, 35, 36, 37, 38, 39, 40};
        int[] arr5 = {41, 42, 43, 44, 45, 46, 47, 48, 49, 50};
        int[] arr6 = {51, 52, 53, 54, 55, 56, 57, 58, 59, 60};
        int[][] matrix = {arr6, arr2, arr4, arr1, arr3, arr5};
        
        int[] mainArr = new int[arr1.length + arr2.length + arr3.length + arr4.length + arr5.length + arr6.length];
        
        sortingServant.merge(matrix,mainArr);
        System.out.println(Arrays.toString(mainArr));


        /*DIVIDEE */
      /*   int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25};
        int k = 5;

        int[][] dividedArray = sortingServant.divideArrayIntoKSubarrays(array, k);

        // Imprimir los subarrays resultantes
      /*   for (int i = 0; i < dividedArray.length; i++) {
            System.out.print("Subarray " + (i + 1) + ": ");
            for (int j = 0; j < dividedArray[i].length; j++) {
                System.out.print(dividedArray[i][j] + " ");
            }
            System.out.println();
        }
        */

    }
}
