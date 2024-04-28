import java.util.HashMap;
import MergeICE.*;
import com.zeroc.Ice.Current;

public class MergeSort implements MergeICE.DistributedSorting {


    
    @Override
    public int[] sort(int[] clientArr,Current current) {
         
        
        
        mergeSort(clientArr);
        return clientArr;
    }

    @Override
    public void initConnection(String ip, String port, Current current){
        System.out.println("Secondary server connected ");
        System.out.println(ip);
        System.out.println(port);
    }



    /* SORTING ALGORITHM */



    public void mergeSort(int []arr){
        if(arr.length==0|| arr.length==1){
            return;
        }
        int [][] subarrays=divideArrayIntoKSubarrays(arr, 2);
        mergeSort(subarrays[0]);
        mergeSort(subarrays[1]);
        merge(subarrays,arr);
    }
    
    public int[][] divideArrayIntoKSubarrays(int[] arr, int k){
        int n=arr.length;
        int q=n/k; 
        int r=n%k;
        int matrix[][]=new int[k][];

        for(int i=0,cont=0;i<k;i++){
            int subsize;
            if(r==0){
                subsize=q;
            }else{
                subsize=q+1;
                r--;
            }
            matrix[i]=new int[subsize];
            fillSubarray(matrix[i], arr, cont);
            cont+=subsize;
        }
        return matrix;
    }
    private void fillSubarray(int[] subArray,int[] mainArray,int start){
        for(int i=0;i<subArray.length;i++){
            subArray[i]=mainArray[start+i];
        }
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