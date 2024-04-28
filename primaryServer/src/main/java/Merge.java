import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import MergeICE.*;
import com.zeroc.Ice.*;
import com.zeroc.Ice.Object;

public class Merge implements MergeICE.DistributedSorting {
    private Communicator communicator;
    private List<DistributedSortingPrx> helperServers;

    public Merge(Communicator communicator){
        this.communicator=communicator;
        this.helperServers=new LinkedList<>();
    }
    
    
    @Override
    public int[] sort(int[] clientArr,Current current) {
        int[][] subarrays=divideArrayIntoKSubarrays(clientArr, helperServers.size());

        List<CompletableFuture<int[]>> completablesFuture=new ArrayList<>();
        for(int i=0;i<helperServers.size();i++){
            CompletableFuture<int[]> completableFuture= (helperServers.get(i)).sortAsync(subarrays[i]);
            completablesFuture.add(completableFuture);
        }
        for(int i=0;i<helperServers.size();i++){
            try {
                int[] result=(int[]) ((completablesFuture.get(i)).get());
                subarrays[i]=result;
                
            } catch (java.lang.Exception e) {
                // TODO: handle exception
            }
            
        }
        merge(subarrays, clientArr);
        return clientArr;
    }

    @Override
    public void initConnection(String ip, String port, Current current){
        System.out.println("Helper server connected ");
        System.out.println(ip);
        System.out.println(port);

        ObjectPrx base = communicator.stringToProxy("MergeICE: tcp -h "+ip+" -p "+port);

        DistributedSortingPrx sorter = DistributedSortingPrx.checkedCast(base);
        if(sorter == null) {
            throw new Error("Invalid proxy");
        }
        helperServers.add(sorter);
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