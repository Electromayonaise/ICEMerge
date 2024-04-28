module MergeICE {
sequence<int> intSeq;

 interface DistributedSorting {
 
    intSeq sort(intSeq clientArray);
    void initConnection(string ip, string port);
 }


}