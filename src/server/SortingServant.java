package server;

import java.util.HashMap;
import java.util.List;


public class SortingServant {
    
    public void mergeSort(int []arr){
        if(arr.length==0|| arr.length==1){
            return;
        }
        int[][] subarrays=splitInHalf(arr);
        mergeSort(subarrays[0]);
        mergeSort(subarrays[1]);
        merge(subarrays,arr);
    }
    private int[][] splitInHalf(int []arr){
        int size1,size2;
        if(arr.length%2==0){ //if the length is even both arrays have the same legth
            size1=arr.length/2;
            size2=size1;
        }else{ //otherwise the length of the first array is greater than the length of the second array
            size1=arr.length/2+1;
            size2=size1-1;
        }
        
        int[] arr1=new int[size1];
        int[] arr2=new int [size2];
        for (int i = 0; i < size1; i++) {
            arr1[i]=arr[i];
        }
        for (int i=size1,j=0 ;   i<arr.length;   i++,j++){
            arr2[j]=arr[i];
        }
        
        return new int[][]{arr1,arr2};
    }
    public void merge(int[][]subarrays, int[] mainArray){
        //we create a hashmap that links each subarray with an iterator
        HashMap<int[],Integer> subarrayIterators=new HashMap<>();
        
        for (int[] subarray : subarrays) {
            subarrayIterators.put(subarray, 0);
        }
        for(int i=0;i<mainArray.length;i++){
            //we look for the subarray whose iterator points to lowest element
            int[] subArrayWithMinVal=null;
            for (int[] subarray: subarrays) {
                Integer iterator=subarrayIterators.get(subarray);
                if(iterator>=subarray.length){ //we have already iterated over each element of this sub array, we exclude it
                    continue;
                }else if(subArrayWithMinVal==null){
                    subArrayWithMinVal=subarray;
                }else{
                    int currentMin=subArrayWithMinVal[subarrayIterators.get(subArrayWithMinVal)];
                    int potentialMin=subarray[subarrayIterators.get(subarray)];
                    if(potentialMin<currentMin){
                        subArrayWithMinVal=subarray;
                    }
                    
                }
            }
            mainArray[i]=subArrayWithMinVal[subarrayIterators.get(subArrayWithMinVal)];
            //we increase the iterator in 1
            subarrayIterators.put(subArrayWithMinVal,subarrayIterators.get(subArrayWithMinVal)+1);

        }
    }
    
    
    
}
