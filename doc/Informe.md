# **Report on the Implementation of a Distributed Ordering System with ICE**

## **Introduction**

The objective of this report is to describe the implementation of a distributed sorting system using ICE (Internet Communications Engine). The distributed system allows to sort large datasets efficiently using multiple help servers.

## **Description of the Problem**

Sorting large data sets can be a computationally expensive challenge. In a distributed environment, the task of sorting this data can be divided among several servers, allowing for faster and more efficient processing.

## **Distribution Strategy**

The distribution strategy implemented in this system is based on the Merge Sort algorithm. The Merge Sort algorithm divides the data set into smaller subsets, sorts them individually and then merges these sorted subsets to obtain the final sorted data set.

In this system, the data set is divided into K subsets, where K is the number of available help servers. Each subset is sent to a help server to be sorted independently. Once all subsets have been sorted by the help servers, the sorted subsets are merged to obtain the final sorted dataset.

## **Implementation Design using ICE**

The implementation design is divided into three main components: the main server, the help servers and the client.

1. **Primary Server:**
   - The primary server acts as the entry point of the distributed system.
   - It is responsible for initializing ICE and creating an object adapter to expose the sorting functionality.
   - When a client requests to sort a dataset, the primary server divides the dataset into subsets and sends them to the helper servers to be sorted.
   - Once the subsets have been sorted by the helper servers, the primary server merges them and returns the sorted dataset to the client.

2. **Helper Servers:**
   - The helper servers are responsible for sorting the subsets of data received from the main server.
   - Each helper server uses the Merge Sort algorithm to sort the subsets of data independently.
   - Once a helper server has sorted a subset of data, it returns the sorted subset to the main server.

3. **Client:**
   - The client allows users to upload data sets to be sorted by the distributed system.
   - The client connects to the primary server using ICE and sends the dataset to the primary server for processing.
   - Once the dataset has been sorted, the client receives the sorted dataset from the primary server and displays it to the user.

## **Complexity of the Merge Sort algorithm implemented with distribution on k servers.**

The implemented Merge Sort algorithm distributes the original array into “k” subarrays, where “k” is the number of available secondary servers. Each secondary server sorts its subarray using the Merge Sort algorithm independently, and then the primary server merges the sorted subarrays to obtain the final array.

$i$. **Division of the array into k subarrays:**

The original array is divided into “k” subarrays of size approximately “n/k” at each step. This is done in a distributed manner on the “k” secondary servers.

- Complexity Time: $O(\frac{n}{k})$

$ii$. **Ordering of the subarrays in parallel:**

Each secondary server sorts its subarray using the Merge Sort algorithm independently.

- Complexity Time: $O(\frac{n}{k} log(\frac{n}{k}))$

$iii$. **Merging of the ordered subarrays:**

The primary server merges the ordered “k” subarrays to obtain the final array.

- Complexity Time: $O(k)$

### Total complexity:

The total time complexity of the Merge Sort algorithm with distribution on “k” servers is:

$O\left(\frac{n}{k} + k \cdot \left(\frac{n}{k} \log\left(\frac{n}{k}\right)\right) + k\right)$

### Implementation analysis

The distributed implementation of the Merge Sort algorithm leverages the processing power of “k” secondary servers to efficiently and scalably sort large data sets. The division of the array into “k” distributed subarrays allows to parallelize the sorting and reduce the overall execution time of the algorithm.

## **Testing Design and Results**

To test the performance of the algorithm, we tested the algorithm with four different data sizes of 100, 1000, 10000 and 100000 data. For all of them the sorting time was checked 11 times, omitting the first one, and then an average of these times was performed, which resulted in the following performance for the algorithm:

|Data size|Average time (ms)|Best time (ms)|Worst time (ms)|
|-|-|-|-|
|100|10.4|0|28|
|1000|19.8|0|43|
|10000|35.8|18|53|
|100000|147.8|115|250|

> It should be noted that these tests were performed on a computer with a "AMD A6-5200 APU with Radeon(TM) HD Graphics 2.00 GHz" processor and 4 GB DDR3 RAM.

## **Conclusion**

The implementation of a distributed sorting system using ICE offers an efficient and scalable solution for sorting large data sets. The Merge Sort algorithm implemented with distribution on “k” servers provides agile performance in distributed environments. The distribution strategy based on Merge Sort significantly reduces time complexity by distributing the workload across multiple secondary servers, allowing for faster and more efficient sorting of large datasets. Overall, the combination of distribution strategy and implementation design using ICE provides a robust and flexible solution to the distributed sorting problem.