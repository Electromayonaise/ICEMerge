package server;

import java.util.Arrays;


public class Main {
    public static void main(String[] args) {
        // Crear un HashMap
        SortingServant sortServant=new SortingServant();
        int[] arr = {9, 8, 4, 2, 5, 2, -1, 500, 1, 2, 20, 100, 55, -10, 7, 33, 45, 11, 3, 6,
            15, 27, 19, 12, 18, 25, 30, 40, 50, 60, 70, 80, 90, 95, 98, 97, 96, 94, 93,
            91, 85, 81, 77, 72, 65, 61, 58, 56, 54, 51, 49, 47, 46, 44, 42, 38, 35, 32,
            29, 28, 26, 23, 21, 17, 16, 14, 13, 10, 0, -5, -15, -20, -25, -30, -40, -50,
            -60, -70, -80, -90};
        sortServant.mergeSort(arr);
        System.out.println("merge sort test");
        System.out.println(Arrays.toString(arr));


        System.out.println("MERGE TEST");
        int[] arr1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] arr2 = {11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
        int[] arr3 = {21, 22, 23, 24, 25, 26, 27, 28, 29, 30};
        int[] arr4 = {31, 32, 33, 34, 35, 36, 37, 38, 39, 40};
        int[] arr5 = {41, 42, 43, 44, 45, 46, 47, 48, 49, 50};
        int[] arr6 = {51, 52, 53, 54, 55, 56, 57, 58, 59, 60};
        int[][] matrix = {arr6, arr2, arr4, arr1, arr3, arr5};
        
        int[] mainArr = new int[arr1.length + arr2.length + arr3.length + arr4.length + arr5.length + arr6.length];
        
        sortServant.merge(matrix,mainArr);
        System.out.println(Arrays.toString(mainArr));


    }
}
